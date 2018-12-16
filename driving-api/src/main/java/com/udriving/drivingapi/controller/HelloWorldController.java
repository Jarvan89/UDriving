package com.udriving.drivingapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udriving.drivingapi.entity.Person;
import com.udriving.drivingapi.entity.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloWorldController {
    @Autowired
    private PersonRepository personRepository;
    @RequestMapping("/hello1")
    public String index() {


        List<Person> list = personRepository.findAll();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (Person p : list)
            map.put(p.getId(), p.getName());
        ObjectMapper json = new ObjectMapper();
        String ss = null;
        try {
            ss = json.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        PrintWriter out = response.getWriter();
//        out.print(ss);
//        out.flush();
//        out.close();


        return ss;
    }
}