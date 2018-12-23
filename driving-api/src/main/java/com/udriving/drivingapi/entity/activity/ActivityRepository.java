package com.udriving.drivingapi.entity.activity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 活动实体类数据库操作接口
 */
public interface ActivityRepository extends JpaRepository<UDActiviti, Integer> {
    /**
     * 根据活动状态查询活动列表
     *
     * @param status 活动状态
     * @return 满足条件的活动列表
     */
    List<UDActiviti> findByStatus(int status);
}
