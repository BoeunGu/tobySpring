package com.tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false) // Bean사이의 상호 호출을 통해서 의존관계 넣을 것이 아니라면 proxy bean은 필요 없음.
public class WebServerConfiguration {

    @Bean
    ServletWebServerFactory customerWebServerFactory() {
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        // 순정상태의 톰캣서버팩토리를 사용하는건 이전 코드의 자동구성에 있는 것과 차이가 없어서 customize를 해본다. => 원하는 여러가지 설정을 추가해줄 수 있다.
        serverFactory.setPort(9090);
        return serverFactory;
    }
}
