package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

//이제 컨트롤러에서 모델과 뷰를 따로 떼어서 전달 할 것이다.
public class ModelView {
    private String viewName; //view의 논리적 이름을 저장할 거임
    private Map<String, Object> model = new HashMap<>(); //모델에 대한 정보

    public ModelView(String viewName) {
        this.viewName = viewName;
    }

    //view에 대한 getter,setter => 롬복으로 사용해줘도 된다.
    public String getViewName() {
        return viewName;
    }
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    //model에 대한 getter,setter => 롬복으로 사용해줘도 된다.
    public Map<String, Object> getModel() {
        return model;
    }
    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}