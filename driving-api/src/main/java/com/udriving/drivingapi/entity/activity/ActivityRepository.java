package com.udriving.drivingapi.entity.activity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *活动实体类数据库操作接口
 */
public interface ActivityRepository extends JpaRepository<UDActiviti, Integer> {
}
