package com.tobyspring.helloboot;

import java.util.Objects;

public class HelloController {
    public String hello(String name) {
        SimpleHelloService helloService = new SimpleHelloService();

        //HelloController와 SimpleHelloService의 역할 분리
        // HelloController : 사용자의 요청이 유효한지 검증
        // SimpleHelloService : 실제 비즈니스 로직 처리
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
