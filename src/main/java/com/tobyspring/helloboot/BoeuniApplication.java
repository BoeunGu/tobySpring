package com.tobyspring.helloboot;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class BoeuniApplication {

    public static void run(Class<?> applicationClass, String[] args) {
        // 자바 코드를 이용한 구성 정보를 사용하려면 AnnotationConfigApplicationContext 클래스로 컨테이너를 만들어야한다.
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext() {
            @Override
            protected void onRefresh() { //스프링 컨테이너를 초기화 할때 서블릿컨테이너를 만들고 서블릿을 초기화 하는 작업을 수행 하도록 변경
                super.onRefresh();

                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
                dispatcherServlet.setApplicationContext(this); // ApplicationContextAware을 상속하고 있기에, DispatcherServlet이 빈으로 등록 될 시점에 스프링 컨테이너도 함께 주입 됨.
                WebServer webServer = serverFactory.getWebServer(new ServletContextInitializer() {
                    @Override
                    public void onStartup(ServletContext servletContext) throws ServletException {

                        servletContext.addServlet("dispatcherServlet", dispatcherServlet)
                                .addMapping("/*"); //FrontController 역할을 담당하도록 모든 요청을 처리하게 한다.
                    }
                });
                webServer.start();

            }
        };
        applicationContext.register(applicationClass);
        applicationContext.refresh(); //컨테이너에 필요한 정보를 등록하고 refresh()를 이용해서 초기화 작업을 진행한다.
    }

}
