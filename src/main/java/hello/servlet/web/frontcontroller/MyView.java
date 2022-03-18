package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {
    private String viewPath;

    //생성자
    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }
    
    // 렌더링 담당 메서드 "렌더링한다 = 그냥 뷰 직접 만들든 사전적의미의 렌더링을 실행하든 일단 뷰에 대한 작업을 하면 다 렌더링 한다라고 봐도 된다."
    //이전 단계에서 각 컨트롤러마다 직접 dispatcher를 사용하던 방식에서 이렇게 뷰로 하나에 묶는 작업을 하는 거다.
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        //v1-v2에서는 서블릿의 response를 계속 끌고 다녔는데 v3에서 model과 view를 분리해 주었다.
        //따라서 따로 분리한 애들을 넘겨받아 서블릿 요청 파라미터에 다시 넣어주는 작업이 필요하다.model로 넘어온 정보를 모두 꺼내서 request에 담아주는 메서드를 실행한다.
        modelToRequestAttribute(model, request);

        //이하 로직은 기존 랜더와 같음. => dispatcher를 통해 뿌리기
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath); // jsp는 .getRequestDispatcher를 통해 뿌리는데, 다른 기술들은 아닌 기술도 있으니 그깨그때 확인할 것
        dispatcher.forward(request, response);
    }

    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        //model로 넘어온 정보를 모두 꺼낸다.
        model.forEach((key, value) -> request.setAttribute(key, value)); //key와 value라는 변수로 꺼내서 request에 다시 저장한다.
    }
}
