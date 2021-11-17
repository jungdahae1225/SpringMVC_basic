package hello.servlet.web.servletmvc;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/****
 *회원 저장 - 컨트롤러
 * ***/
@WebServlet(name = "mvcMemberSaveServlet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //컨트롤러의 역할1.파라미터 받기
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        //컨트롤러의 역할2.비지니스 로직수행
        Member member = new Member(username, age);
        System.out.println("member = " + member);
        memberRepository.save(member);
        //=========================================여기까지는 일반 서블릿 코드랑 같음 ====================================//

        //컨트롤러의 역할3.Model에 .setAttribute()로 데이터를 보관한다. (Model은 HttpServletRequest 객체를 사용한다.)
        request.setAttribute("member", member);
        String viewPath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
     }
}
