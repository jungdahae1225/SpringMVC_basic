package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

//컨트롤러가 서블릿에 종속하는 것을 해결하고자 model를 따로 때어내는 것이다.
public class ModelView {
    private String viewName; //view의 논리적 이름을 저장할 거임
    private Map<String, Object> model = new HashMap<>();

    public ModelView(String viewName) {
        this.viewName = viewName;
    }

    //getter,setter
    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}