package com.udriving.drivingapi;

//import org.glassfish.jersey.server.ResourceConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DrivingApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(DrivingApiApplication.class, args);
    }
//    @Bean
//    public ResourceConfig resourceConfig() {
//        ResourceConfig config = new ResourceConfig();
//        config.register(DrivingApiApplication.class);
//        return config;
//    }

}
