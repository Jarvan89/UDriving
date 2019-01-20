package com.udriving.drivingapi.entity.dao;

import com.udriving.drivingapi.entity.activity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Coder shihaiyang
 * @Date 2019-01-07 10:56
 */
public interface UDRoleRespository extends JpaRepository<UDRole, Long> {


    @Query(value = "select * from UD_Role WHERE name = :name ORDER BY id DESC ", nativeQuery = true)
    UDRole findByName(@Param("name") String name);
}
