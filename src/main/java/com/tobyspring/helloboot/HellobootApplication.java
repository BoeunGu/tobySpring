package com.tobyspring.helloboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
public class HellobootApplication {

    public static void main(String[] args) {

        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
        applicationContext.registerBean(HelloController.class); // bean 등록
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.refresh(); //컨테이너에 필요한 정보를 등록하고 refresh()를 이용해서 초기화 작업을 진행한다.

        //스프링 부트가 지원하는 'TomcatServletWebServerFactory'를 사용하면 톰캣 웹 서버(서블릿 컨테이너)를 실행하는 코드를 만들 수 있다.
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {

                servletContext.addServlet("dispatcherServlet", new DispatcherServlet(applicationContext))
                        .addMapping("/*"); //FrontController 역할을 담당하도록 모든 요청을 처리하게 한다.
            }
        });
        webServer.start();

    }

}
