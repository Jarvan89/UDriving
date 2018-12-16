package com.udriving.drivingapi.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/12/11
 */
@Entity
public class Person {
    @Id
    @GeneratedValue
    private int id;
    private int name;

    public Person() {

    }

    public Person(int id, int name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
