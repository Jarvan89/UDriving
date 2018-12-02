package com.udriving.drivingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
public class DrivingApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(DrivingApiApplication.class, args);
    }
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        return "test success";
    }

}
