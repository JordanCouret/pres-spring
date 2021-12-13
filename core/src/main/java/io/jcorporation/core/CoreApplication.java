package io.jcorporation.core;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CoreApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        context.getBean(UserService.class)
            .create(Arrays.stream(args).findFirst().orElseThrow(() -> new RuntimeException("Pas de login en argument de la fonction")));
    }

}
