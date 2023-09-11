package com.tobyspring.helloboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpMethod;
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

        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.registerBean(HelloController.class); // bean 등록
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.refresh(); //컨테이너에 필요한 정보를 등록하고 refresh()를 이용해서 초기화 작업을 진행한다.

        //스프링 부트가 지원하는 'TomcatServletWebServerFactory'를 사용하면 톰캣 웹 서버(서블릿 컨테이너)를 실행하는 코드를 만들 수 있다.
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {


                //서블릿 등록
                servletContext.addServlet("frontController", new HttpServlet() {
                    @Override
                    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                        // 인증, 보안, 다국어 등 공통 기능을 Front Controller가 담당하도록 함.
                        if (req.getRequestURI().equals("/hello")
                                && req.getMethod().equals(HttpMethod.GET.name())) {
                            String name = req.getParameter("name");

                            HelloController helloController = applicationContext.getBean(HelloController.class);
                            String ret = helloController.hello(name);

                            resp.setContentType("Text/plain");
                            resp.getWriter().println(ret);
                        } else if (req.getRequestURI().equals("/user")) {
                            //
                        } else {
                            resp.setStatus(HttpStatus.NOT_FOUND.value());
                        }

                    }
                }).addMapping("/*"); //FrontController 역할을 담당하도록 모든 요청을 처리하게 한다.
            }
        });
        webServer.start();

    }

}
