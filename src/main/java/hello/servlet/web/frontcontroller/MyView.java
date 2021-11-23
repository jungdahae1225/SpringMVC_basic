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
    
    // 렌더링 담당 메서드 "렌더링한다 = 그냥 뷰 직접 만들든 ㄸ사전적의미의 렌더링을 실행하든 일단 뷰에 대한 작업을 하면 다 렌더링 한다라고 봐도 된다."
    //이전 단계에서 각 컨트롤러마다 직접 dispatcher를 사용하던 방식에서 이렇게 뷰로 하나에 묶는 작업을 하는 거다.
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        modelToRequestAttribute(model, request);//model로 넘어온 정보를 모두 꺼내서 request에 담아주는 메서드를 실행한다.

        //이하 로직은 기존 랜더와 같음.
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        //model로 넘어온 정보를 모두 꺼낸다.
        model.forEach((key, value) -> request.setAttribute(key, value)); //key와 value라는 변수로 꺼내서 request에 다시 저장한다.
    }
}
