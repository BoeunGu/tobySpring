package com.tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Configuration // 해당 애노테이션이 붙은 클래스가 AnnotationConfigApplicationContext에 처음 등록되는 빈 이다.
public class HellobootApplication {

    public static void main(String[] args) {

        // 자바 코드를 이용한 구성 정보를 사용하려면 AnnotationConfigApplicationContext 클래스로 컨테이너를 만들어야한다.
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext() {
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
        applicationContext.register(HellobootApplication.class);
        applicationContext.refresh(); //컨테이너에 필요한 정보를 등록하고 refresh()를 이용해서 초기화 작업을 진행한다.


    }

    @Bean // 스프링 컨테이너에게 bean 등록에 사용될 factory method라고 알려주는 역할.
    public HelloController helloController(HelloService helloService) {
        return new HelloController(helloService);
    }

    @Bean
    public HelloService helloService() {
        return new SimpleHelloService();
    }

}
