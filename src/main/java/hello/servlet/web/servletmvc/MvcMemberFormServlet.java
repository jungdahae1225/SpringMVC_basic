package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*********
 * 서블릿을 컨트롤러로 사용하고, JSP를 뷰로 사용해서 MVC 패턴을 적용해보자.
 * Model은 HttpServletRequest 객체를 사용한다.
 * (request는 내부에 데이터 저장소를 가지고 있는데, request.setAttribute() , request.getAttribute() 를 사용하면 데이터를 보관하고, 조회할 수 있다.)
 * ********/

/****
 *회원 등록 폼 - 컨트롤러
 * ***/

//서블릿을 컨트롤러로 사용한다.
@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //MVC패턴에서는 항상 데이터가 컨트롤러를 거쳐서 뷰로 들어가야 한다.
        String viewPath = "/WEB-INF/views/new-form.jsp";

        /**dispatcher -> 컨트롤러에서 뷰로 보낼 때 사용. (여기서는 서블릿에서 jsp파일(view)을 불러오기 위해 사용.)
         * .forward()서버 내부에서 로직이 돌아가 서버로직->클라이언트->다시 서버 와 같이 2번 호출이 일어나는 redirect와 달리,
         * 서버 내에서 로직을 처리하여 서버 -> 클라이언트로의 호출 1번만을 수행한다.
         * **/

        /******
         * 로직
         * 1.고객의 요청이 오면 이 class 코드가 실행됨.
         * 2.dispatcher에 의해 String viewPath = "/WEB-INF/views/new-form.jsp" 이 경로가 호출됨
         * => WEB-INF이 경로안에 JSP가 있으면 외부에서 직접 JSP를 호출할 수 없다. 항상 컨트롤러를 통해서 JSP를 호출하기 위해 이 곳에 넣어주는 것이다.
         * 3.위의 결과에 따라 서버 내부에서 컨트롤러와 뷰 파일이 연결? 되는 역할 수행
         * *****/

        //Model에서 데이터를 가져온다. (Model은 HttpServletRequest 객체를 사용한다.)
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
 }