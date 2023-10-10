package com.tobyspring.config.autoConfig;

import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Configuration // MyAutoConfiguration을 사용하면 @Configuration 클래스로 선언한 것과 동일한 효과를 나타냄
public @interface MyAutoConfiguration {


}
