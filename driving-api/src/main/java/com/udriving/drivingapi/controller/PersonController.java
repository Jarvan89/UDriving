package com.udriving.drivingapi.controller;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/12/11
 */


import com.fasterxml.jackson.databind.ObjectMapper;
import com.udriving.drivingapi.entity.Person;
import com.udriving.drivingapi.entity.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    //查看全部
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public void sau(HttpServletResponse response) throws IOException {

        List<Person> list = personRepository.findAll();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (Person p : list)
            map.put(p.getId(), p.getName());
        ObjectMapper json = new ObjectMapper();
        String ss = json.writeValueAsString(map);
        PrintWriter out = response.getWriter();
        out.print(ss);
        out.flush();
        out.close();
    }

    //添加一个
    @PostMapping(value = "/hello")
    public Person addPerson(@RequestParam("id") Integer id, @RequestParam("name") Integer name) {
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        return personRepository.save(person);
    }

    @GetMapping(value = "/hello/add")
    public Person addPerson1(@RequestParam("id") Integer id, @RequestParam("name") Integer name) {
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        return personRepository.save(person);
    }


    //修改一个
    @PutMapping(value = "/hello/{id}")
    public Person updatePerson(@PathVariable("id") Integer id, @RequestParam("name") Integer name) {
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        return personRepository.save(person);
    }

    @GetMapping(value = "/hello/upd")
    public Person updPerson(@RequestParam("id") Integer id, @RequestParam("name") int name) {//@PathVariable("id") Integer id){
        Person person = findOne(id);
        person.setName(name);
        return personRepository.save(person);
    }

    //查找一个
    @GetMapping(value = "/hello/{id}")
    public Person findOne(@PathVariable("id") Integer id) {
        return personRepository.findById(id).orElse(null);
    }

    //删除
    @GetMapping(value = "/hello/del/{id}")
    public void deletePerson(@PathVariable("id") Integer id) {
        Person person = findOne(id);
        personRepository.delete(person);
    }
}
