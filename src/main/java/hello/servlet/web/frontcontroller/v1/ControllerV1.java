package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV1 { //다형성을 위해 인터페이스로 구현 => 각 컨트롤러들은 이 인터페이스를 구현하면 된다.

    //서블릿 인터페이스를 구현한다.
    //basic 서블릿 코드에서 service 메서드와 같음
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
