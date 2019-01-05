package com.udriving.drivingapi.entity.activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 活动实体类数据库操作接口
 */
public interface ActivityRepository extends JpaRepository<Activity, Integer> {
    /**
     * 根据活动状态查询活动列表
     *
     * @param status 活动状态
     * @return 满足条件的活动列表
     */
    List<Activity> findByStatus(int status);

    /**
     * 根据活动状态查询指定数量的活动列表
     *
     * @param status   活动状态
     * @param dataSize 指定活动数量
     * @return 活动列表
     */
    @Query(value = "select * from activitys WHERE status = :status ORDER BY id DESC LIMIT :dataSize", nativeQuery = true)
    List<Activity> queryAllCanApplyActivity(@Param("status") byte status, @Param("dataSize") byte dataSize);

    /**
     * 根据活动状态和起始id查询指定数量的活动列表
     *
     * @param startId  起始id
     * @param status   活动状态
     * @param dataSize 指定活动数量
     * @return 活动列表
     */
    @Query(value = "select * from activitys WHERE id <= :startId AND status = :status ORDER BY id DESC LIMIT :dataSize", nativeQuery = true)
    List<Activity> queryAllCanApplyActivity(@Param("startId") int startId, @Param("status") byte status, @Param("dataSize") byte dataSize);
}
