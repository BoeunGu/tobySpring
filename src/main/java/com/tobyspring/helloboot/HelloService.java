package com.tobyspring.helloboot;

public interface HelloService {

    // 인터페이스를 통해서 코드레벨에서의 의존관계를 없애고, 동적으로 스프링 컨테이너(assembler)를 이용하여
    // bean간의 연관관계를 주입이라는 방법을 사용해서 지정할 수 있다.
    String sayHello(String name);
}
