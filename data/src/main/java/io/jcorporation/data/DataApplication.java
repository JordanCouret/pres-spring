package io.jcorporation.data;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@Configuration Todo : voir comment fiare avec les annotations
//@ComponentScan(basePackageClasses = DataApplication.class)
public class DataApplication {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml", "application-repository.xml");
        context.getBean(UserService.class)
            .create(Arrays.stream(args).findFirst().orElseThrow(() -> new RuntimeException("Pas de login en argument de la fonction")));
    }
}
