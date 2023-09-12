package com.tobyspring.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@RequestMapping
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    } // 생성자 주입


    @GetMapping("/hello")
    @ResponseBody
    // @GetMapping 애노테이션 : DispatcherServlet이 web 요청을 처리할 수 있는 클래스로 인식하고 매핑 테이블에 저장한다.
    // @ResponseBody 애노테이션 : String을 리턴하는 컨트롤러를 DispatcherServlet이 처리하는 가장 기본적인 방법은, 해당 이름의 view(html template)를 찾는 것이다.
    // 해당 애노테이션을 사용하면 리턴 값을 웹 응답의 body에 넣에 text/plain 형태로 응답하게 해준다.
    public String hello(String name) {
        //HelloController와 SimpleHelloService의 역할 분리
        // HelloController : 사용자의 요청이 유효한지 검증
        // SimpleHelloService : 실제 비즈니스 로직 처리
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
