package com.malang.webservice.mobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class Application {
//    public static final String APPLICATION_LOCATIONS = "spring.config.location="
//            + "classpath:application.properties,"
//            + "classpath:application-oauth.properties,"
//            + "classpath:application-real.properties,"
//            + "classpath:application-real1.properties,"
//            + "classpath:application-real2.properties,"
//            + "classpath:aws.properties";
//
//    public static void main(String[] args) {
//        new SpringApplicationBuilder(Application.class)
//                .properties(APPLICATION_LOCATIONS)
//                .run(args);
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
