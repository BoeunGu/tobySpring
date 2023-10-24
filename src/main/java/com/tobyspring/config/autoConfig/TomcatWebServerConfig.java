package com.tobyspring.config.autoConfig;

import com.tobyspring.config.ConditionalMyOnClass;
import com.tobyspring.config.MyAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfig {

    @Bean("tomcatWebServerFactory")
    @ConditionalOnMissingBean // 사용자가 직접 등록한 Bean이 있는지 확인하고 없으면 해당 메소드의 Bean을 주입시킴.
    // 'helloboot'패키지의 Bean들이 먼저 등록되고 나서 자동구성 정보 Bean들이 주입됨.
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }


}
