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
public interface UDUserRepository extends JpaRepository<UDUser, Long> {

    @Query(value = "select * from UDUser WHERE openId = :openId ORDER BY id DESC", nativeQuery = true)
    UDUser findUserByOpenId(@Param("openId") String status);

}
