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

        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext() {
            @Override
            protected void onRefresh() { //스프링 컨테이너를 초기화 할때 서블릿컨테이너를 만들고 서블릿을 초기화 하는 작업을 수행 하도록 변경
                super.onRefresh();
                TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                WebServer webServer = serverFactory.getWebServer(new ServletContextInitializer() {
                    @Override
                    public void onStartup(ServletContext servletContext) throws ServletException {

                        servletContext.addServlet("dispatcherServlet", new DispatcherServlet(this))
                                .addMapping("/*"); //FrontController 역할을 담당하도록 모든 요청을 처리하게 한다.
                    }
                });
                webServer.start();

            }
        };
        applicationContext.registerBean(HelloController.class); // bean 등록
        applicationContext.registerBean(SimpleHelloService.class); // bean 등록
        applicationContext.refresh(); //컨테이너에 필요한 정보를 등록하고 refresh()를 이용해서 초기화 작업을 진행한다.


    }

}
