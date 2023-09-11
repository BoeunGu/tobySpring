package com.tobyspring.helloboot;

import java.util.Objects;

public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    } // 생성자 주입

    public String hello(String name) {

        //HelloController와 SimpleHelloService의 역할 분리
        // HelloController : 사용자의 요청이 유효한지 검증
        // SimpleHelloService : 실제 비즈니스 로직 처리
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
