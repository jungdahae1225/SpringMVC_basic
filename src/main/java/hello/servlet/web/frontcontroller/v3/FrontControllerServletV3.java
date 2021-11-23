package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")//v1하위의 api모두 이 클래스의 서블릿이 실행되도록
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service"); //실행여부 확인
        String requestURI = request.getRequestURI(); //주소창에서 들어온 주소를 고대로 받을 수 있음. ex) /front-controller/v1/members

        ControllerV3 controller = controllerMap.get(requestURI);// 윗줄 코드에서 얻은 api를 이미 저장해 놓은 map에서 찾으면 해당 api와 연결되어 있는 컨트롤러를 꺼내 올 수 있음.
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);//404에서
            return;
        }

        //map생성
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap); //현재 뷰의 논리 주소만 얻음. -> 실제 주소로 만들어줘야함

        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName); //viewResolver 메서드를 통해 뷰의 실제 주소를 공통으로 처리한다.
        view.render(mv.getModel(), request, response);
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator() //넘어오는 request의 모든 파라미터 이름을 다 가져온다.
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName))); //request에서 꺼낸 걸 map에 put하는 과정.
        return paramMap;
    }

    /*뷰 리졸버 컨트롤러가 반환하는 논리 뷰 이름을 실제 물리 뷰 결로로 변환하는 역할. => 굳이 이렇게 분리하는 이유는 나중에 물리뷰의 경로를 변경해야 하는 경우, 모든 컨트롤러를 건들이지 않고도 이 뷰 리졸버만 고치면 되므로 코드 유지보수가 쉽다.*/
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}