package com.tobyspring.helloboot;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary // DI시 해당 빈을 우선적으로 주입 함.
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
