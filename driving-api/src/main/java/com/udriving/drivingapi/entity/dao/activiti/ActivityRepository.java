package com.udriving.drivingapi.entity.dao.activiti;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 活动实体类数据库操作接口
 */
//todo 需要添加更新接口
public interface ActivityRepository extends JpaRepository<UDActivity, Long> {
    /**
     * 根据活动状态查询活动列表
     *
     * @param status 活动状态
     * @return 满足条件的活动列表
     */
    List<UDActivity> findByStatus(int status);

    /**
     * 根据活动状态查询指定数量的活动列表
     *
     * @param status   活动状态
     * @param dataSize 指定活动数量
     * @return 活动列表
     */
    @Query(value = "select * from activitys WHERE status = :status ORDER BY id DESC LIMIT :dataSize", nativeQuery = true)
    List<UDActivity> queryAllCanApplyActivity(@Param("status") byte status, @Param("dataSize") byte dataSize);

    /**
     * 根据活动状态和起始id查询指定数量的活动列表
     *
     * @param startId  起始id
     * @param status   活动状态
     * @param dataSize 指定活动数量
     * @return 活动列表
     */
    @Query(value = "select * from activitys WHERE id < :startId AND status = :status ORDER BY id DESC LIMIT :dataSize", nativeQuery = true)
    List<UDActivity> queryAllCanApplyActivity(@Param("startId") int startId, @Param("status") byte status, @Param("dataSize") int dataSize);

    /**
     * 根据起始id、创建用户id查询最多指定数量的活动列表
     *
     * @param startId      起始活动id
     * @param createUserId 活动创建用户id
     * @param dataSize     数据量
     * @return 活动列表
     */
    @Query(value = "select * from activitys WHERE createUserId = :createUserId AND id < :startId ORDER BY id DESC LIMIT :dataSize", nativeQuery = true)
    List<UDActivity> findActivityByCreateUserId(@Param("startId") int startId, @Param("createUserId") int createUserId, @Param("dataSize") byte dataSize);

    /**
     * 根据创建用户id查询最多指定数量的活动列表
     *
     * @param createUserId 活动创建用户id
     * @param dataSize     数据量
     * @return 活动列表
     */
    @Query(value = "select * from activitys WHERE createUserId = :createUserId ORDER BY id DESC LIMIT :dataSize", nativeQuery = true)
    List<UDActivity> findActivityByCreateUserId(@Param("createUserId") int createUserId, @Param("dataSize") byte dataSize);

    /**
     * 查询小于指定id和最多指定数量的小于某个状态值得活动列表
     *
     * @param startId  起始活动id
     * @param dataSize 数据量
     * @return 活动列表
     */
    @Query(value = "select * from activitys WHERE status <= :status AND id < :startId ORDER BY id DESC LIMIT :dataSize", nativeQuery = true)
    List<UDActivity> findLessStatusActivityList(@Param("startId") int startId, @Param("status") byte status, @Param("dataSize") byte dataSize);

    /**
     * 查询最多指定数量的小于某个状态值的活动列表
     *
     * @param dataSize 数据量
     * @return 活动列表
     */
    @Query(value = "select * from activitys WHERE status <= :status ORDER BY id DESC LIMIT :dataSize", nativeQuery = true)
    List<UDActivity> findLessStatusActivityList(@Param("status") byte status, @Param("dataSize") byte dataSize);
    /**
     * 查询Id最大的活动
     *
     * @return 活动
     */
    @Query(value = "select * from activitys  ORDER BY id DESC LIMIT 1", nativeQuery = true)
    UDActivity findIdMaxActivity();


}
