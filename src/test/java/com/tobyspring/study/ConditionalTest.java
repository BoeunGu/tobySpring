package com.tobyspring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class ConditionalTest {

    @Test
    void conditional() {

        // ApplicationContextRunner는 Spring Framework에서 사용되는 테스트 지원 도구 중 하나
        // true : spring context에 bean으로 등록이 되는 경우
        ApplicationContextRunner contextRunner = new ApplicationContextRunner();
        contextRunner.withUserConfiguration(Config1.class)
                .run(context -> {
                    Assertions.assertThat(context).hasSingleBean(MyBean.class);
                    // @Configuration 애노테이션이 붙은 클래스 자체도 Bean으로 등록된다.
                    Assertions.assertThat(context).hasSingleBean(Config1.class);
                });


        // false : spring context에 bean으로 등록이 되지 않는 경우
        new ApplicationContextRunner().withUserConfiguration(Config2.class)
                .run(context -> {
                    Assertions.assertThat(context).doesNotHaveBean(MyBean.class);
                    // @Configuration 애노테이션이 붙은 클래스 자체도 Bean으로 등록된다.
                    Assertions.assertThat(context).doesNotHaveBean(Config1.class);
                });
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(FlaseCondition.class)
    @interface FalseConditional {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(TrueCondition.class)
    @interface TrueConditional {
    }

    static class TrueCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return true;
        }
    }

    static class FlaseCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return false;
        }
    }

    @Configuration
    @TrueConditional
    static class Config1 {

        @Bean
        MyBean myBean() {
            return new MyBean();
        }
    }

    @Configuration
    @FalseConditional
    static class Config2 {
    }

    static class MyBean {
    }

}
