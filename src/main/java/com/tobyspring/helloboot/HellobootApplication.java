package com.tobyspring.helloboot;

@MySpringBootAnnotation
public class HellobootApplication {

    public static void main(String[] args) {
        BoeuniApplication.run(HellobootApplication.class, args);
        // @Configuration 애노테이션이 붙은 메인 클래스 정보를 넘겨주면, 서블릿등록과 스프링 컨테이너 초기화 작업이 진행 됨.
    }


}
