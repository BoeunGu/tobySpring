package com.tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration // 해당 애노테이션이 붙은 클래스가 AnnotationConfigApplicationContext에 처음 등록되는 빈 이다.
@ComponentScan // 해당 클래스가 있는 패키지 부터 하위 패키지 까지 @Component 애노테이션이 붙어있는 클래스들을 빈으로 등록시켜 줌.
public class HellobootApplication {

    public static void main(String[] args) {
        BoeuniApplication.run(HellobootApplication.class, args);
        // @Configuration 애노테이션이 붙은 메인 클래스 정보를 넘겨주면, 서블릿등록과 스프링 컨테이너 초기화 작업이 진행 됨.
    }


    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }
}
