package com.tobyspring.config;

import com.tobyspring.config.autoConfig.DispatcherServletConfig;
import com.tobyspring.config.autoConfig.TomcatWebServerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({DispatcherServletConfig.class, TomcatWebServerConfig.class}) // @Component가 붙은 클래스를 빈으로 등록할 수 있음.

public @interface EnableMyAutoConfiguration {
}
