package com.udriving.drivingapi.entity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @Coder shihaiyang
 * @Date 2019-01-07 10:56
 */
public interface UDPrivilegeRespository extends JpaRepository<UDPrivilege, Long> {

    @Query(value = "select * from UD_Privilege WHERE name = :name ORDER BY id DESC ", nativeQuery = true)
    UDPrivilege findByName(@Param("name") String name);
}
