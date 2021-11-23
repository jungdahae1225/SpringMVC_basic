package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")//v1하위의 api모두 이 클래스의 서블릿이 실행되도록
public class FrontControllerServletV1 extends HttpServlet {
    private Map<String, ControllerV1> controllerMap = new HashMap<>();
    
    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service"); //실행여부 확인
        String requestURI = request.getRequestURI(); //주소창에서 들어온 주소를 고대로 받을 수 있음. ex) /front-controller/v1/members
        
        ControllerV1 controller = controllerMap.get(requestURI);// 윗줄 코드에서 얻은 api를 이미 저장해 놓은 map에서 찾으면 해당 api와 연결되어 있는 컨트롤러를 꺼내 올 수 있음.
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);//404에서
            return;
        }
        controller.process(request, response); //정상적으로 잘 호출 되었으면 열결 되어있는 컨트롤러 호출해주면 됨.
    }
}