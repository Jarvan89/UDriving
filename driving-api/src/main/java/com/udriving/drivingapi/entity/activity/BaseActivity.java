package com.udriving.drivingapi.entity.activity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 基础活动实体类
 */
@Data
@Entity(name = "activitys")
public class BaseActivity {
    /**
     * 活动id（年月日时分秒+流水，流水号为4位）
     */
    @Id
    protected Long id;
    /**
     * 活动名
     */
    @Column
    protected String title;
    /**
     * 距离
     */
    @Column
    protected float distance;
    /**
     * 预估费用
     */
    @Column
    protected float estimateCost;

    /**
     * 创建人用户名，该名仅针对本次活动有效
     */
    @Column
    private String createUserName;
}
