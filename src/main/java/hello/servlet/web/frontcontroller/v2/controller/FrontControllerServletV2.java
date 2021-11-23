package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v1.ControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import hello.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")//v1하위의 api모두 이 클래스의 서블릿이 실행되도록
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV2.service"); //실행여부 확인
        String requestURI = request.getRequestURI(); //주소창에서 들어온 주소를 고대로 받을 수 있음. ex) /front-controller/v1/members
        
        ControllerV2 controller = controllerMap.get(requestURI);// 윗줄 코드에서 얻은 api를 이미 저장해 놓은 map에서 찾으면 해당 api와 연결되어 있는 컨트롤러를 꺼내 올 수 있음.
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);//404에서
            return;
        }

        MyView view = controller.process(request, response);//정상적으로 잘 호출 되었으면 열결 되어있는 컨트롤러 호출해주면 됨.
        //위에서 map에서 가져온 적합한 컨트롤러를 돌리고 여기서 반환된 뷰로 -> 이걸 렌더링한다.
        view.render(request,response);
    }
}