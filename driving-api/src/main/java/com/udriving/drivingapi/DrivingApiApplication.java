package com.udriving.drivingapi;

//import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
