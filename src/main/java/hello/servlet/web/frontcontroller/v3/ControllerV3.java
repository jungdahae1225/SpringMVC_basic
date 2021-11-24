package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface ControllerV3 { //다형성을 위해 인터페이스로 구현 => 각 컨트롤러들은 이 인터페이스를 구현하면 된다.
    //v1의 컨트롤러는 반환형 void => void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    
    //v2버전 MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    //v3 -v1과 v2에서 사용했던 서블릿 종속pt를 없앤다.
    //process함수
    ModelView process(Map<String, String> paramMap);

}
