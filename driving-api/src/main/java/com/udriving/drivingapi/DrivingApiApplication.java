package com.udriving.drivingapi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DrivingApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(DrivingApiApplication.class, args);
//         BCryptPasswordEncoder encod = new BCryptPasswordEncoder();
//        System.out.println( encod.encode("123"));
    }
//    @Bean
//    public ResourceConfig resourceConfig() {
//        ResourceConfig config = new ResourceConfig();
//        config.register(DrivingApiApplication.class);
//        return config;
//    }

}
