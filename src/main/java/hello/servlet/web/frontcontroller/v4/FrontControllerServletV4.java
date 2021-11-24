package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")//v1하위의 api모두 이 클래스의 서블릿이 실행되도록
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV4.service"); //실행여부 확인
        String requestURI = request.getRequestURI(); //주소창에서 들어온 주소를 고대로 받을 수 있음. ex) /front-controller/v1/members

        ControllerV4 controller = controllerMap.get(requestURI);// 윗줄 코드에서 얻은 api를 이미 저장해 놓은 map에서 찾으면 해당 api와 연결되어 있는 컨트롤러를 꺼내 올 수 있음.
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);//404에서
            return;
        }

        //map생성
        Map<String, String> paramMap = createParamMap(request); // 넘어온 응답에서 파라미터를 모두 꺼내는 작업
        Map<String, Object> model = new HashMap<>(); //v4에서 추가

        //이렇게 파라미터들을 담은 map정보를 ModelView에 넘겨준다.
        String viewName = controller.process(paramMap,model); //여기까지 현재상태 뷰의 논리 주소만 얻음. -> 실제 주소로 만들어줘야함

        //위 메소드 로직을 통해 이렇게 파라미터들을 담은 map정보를
        MyView view = viewResolver(viewName); //viewResolver 메서드를 통해 뷰의 실제 주소를 공통으로 처리한다.
        view.render(model, request, response);
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();

        /**아래의 람다식을 설명하자면,
         * 1. request에 담겨있는 넘어온 정보파라미터를 모두 자른다.getParameterNames()로 자른다.
         * 2. .forEachRemaining의 foreach 로직을 통해 foreach를 돌면서 request.getParameter(paramName)을 통해 파라미미터를 자르고
         * paramName변수에 넣고 -> 자른 파라미터들을 paramMap에 넣는다.(put)
         * **/
        request.getParameterNames().asIterator() //넘어오는 request의 모든 파라미터 이름을 다 가져온다.
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName))); //request에서 꺼낸 걸 map에 put하는 과정.
        return paramMap;

    }

    /*뷰 리졸버 컨트롤러가 반환하는 논리 뷰 이름을 실제 물리 뷰 결로로 변환하는 역할.
    => 굳이 이렇게 분리하는 이유는 나중에 물리뷰의 경로를 변경해야 하는 경우, 모든 컨트롤러를 건들이지 않고도 이 뷰 리졸버만 고치면 되므로 코드 유지보수가 쉽다.*/
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}