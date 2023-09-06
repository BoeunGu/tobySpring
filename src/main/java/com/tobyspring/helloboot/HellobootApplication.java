package com.tobyspring.helloboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;

@SpringBootApplication
public class HellobootApplication {

    public static void main(String[] args) {

        //스프링 부트가 지원하는 'TomcatServletWebServerFactory'를 사용하면 톰캣 웹 서버(서블릿 컨테이너)를 실행하는 코드를 만들 수 있다.
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer();
        webServer.start();

    }

}
