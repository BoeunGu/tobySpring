package com.tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

@MySpringBootAnnotation
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
