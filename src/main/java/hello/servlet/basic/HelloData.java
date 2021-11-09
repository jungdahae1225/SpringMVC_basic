package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HelloData {
    //넘어오는 Json값을 객체화 하기 위함
    private String username;
    private int age;
}
