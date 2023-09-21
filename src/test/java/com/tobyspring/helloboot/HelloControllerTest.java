package com.tobyspring.helloboot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class HelloControllerTest {

    @Test
    void helloController() {
        HelloController helloController = new HelloController(name -> name);
        String ret = helloController.hello("Test");

        assertThat(ret).isEqualTo("Test");
    }

    @Test
    void helloController_Fail() {
        HelloController helloController = new HelloController(name -> name);

        assertThatThrownBy(() -> {
            String ret = helloController.hello(null);
        }).isInstanceOf(NullPointerException.class);

    }
}
