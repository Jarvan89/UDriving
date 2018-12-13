package com.udriving.drivingapi.entity;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/12/11
 */


import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
