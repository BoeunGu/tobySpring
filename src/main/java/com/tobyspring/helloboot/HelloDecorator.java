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
        // 실제 비즈니스 로직 전, 후로 다른 성격의 로직을 수행할때 사용 됨.
        return "*" + helloService.sayHello(name) + "*";
    }

}
