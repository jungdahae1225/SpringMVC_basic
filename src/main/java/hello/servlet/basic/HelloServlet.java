package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello") ///hello로 들어오면 해당 HelloServlet이 실행되는 것
public class HelloServlet extends HttpServlet {

    // ctrl + o
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.service(request, response);

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        /**http://localhost:8080/hello?username=kim 이라는 api를 요청했을 경우**/
        //request 작업
        String username = request.getParameter("username"); //get parameter로 들어온 애를 잘라내는 작업. (이 예제에서는 kim)
        System.out.println("username = " + username);

        //resopnse 작업
        response.setContentType("text/plain");//위의 api의 header에 들어갈 요청을 작성하는 작업
        response.setCharacterEncoding("utf-8");//위의 api의 header에 들어갈 요청을 작성하는 작업
        response.getWriter().write("hello " + username);//위의 api의 body에 들어갈 요청을 작성하는 작업
    }
}
