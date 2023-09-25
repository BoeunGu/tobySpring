package com.tobyspring.helloboot;

import org.springframework.stereotype.Component;

@Component
public class HelloDecorator implements HelloService {
    //데코레이터 패턴
    private final HelloService helloService;

    public HelloDecorator(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public String sayHello(String name) {
        return "*" + helloService.sayHello(name) + "*";
    }

}
