package com.tobyspring.helloboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SpringBootApplication
public class HellobootApplication {

    public static void main(String[] args) {

        //스프링 부트가 지원하는 'TomcatServletWebServerFactory'를 사용하면 톰캣 웹 서버(서블릿 컨테이너)를 실행하는 코드를 만들 수 있다.
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                //서블릿 등록
                servletContext.addServlet("hello",new HttpServlet(){
                    @Override
                    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                        //request에서 쿼리파람 얻는 방법
                        String name = req.getParameter("name");

                        resp.setStatus(HttpStatus.OK.value());
                        resp.setHeader(HttpHeaders.CONTENT_TYPE, "Text/plain");
                        resp.getWriter().println("Hello Servlet" + name);

                    }
                }).addMapping("/hello");
            }
        });
        webServer.start();

    }

}
