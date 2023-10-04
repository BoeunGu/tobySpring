package com.tobyspring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 기존 자바 애노테이션의 RetentionPolicy는 class인데,
// 즉, 애노테이션 정보가 컴파일도니 클래스 파일까지는 살아 있지만, 런타임 로딩시에는 정보가 사라지므로, 해당 애노테이션의 정보를 유지하기 위해서 RetentionPolicy를 런타임으로 지정.
@Target(ElementType.TYPE) // TYPE은 클래스, 인터페이서, 이넘에 해당 함.
@Configuration // 해당 애노테이션이 붙은 클래스가 AnnotationConfigApplicationContext에 처음 등록되는 빈 이다.
@ComponentScan // 해당 클래스가 있는 패키지 부터 하위 패키지 까지 @Component 애노테이션이 붙어있는 클래스들을 빈으로 등록시켜 줌.
@EnableMyAutoConfiguration //Meta Annotation
public @interface MySpringBootApplication {
}
