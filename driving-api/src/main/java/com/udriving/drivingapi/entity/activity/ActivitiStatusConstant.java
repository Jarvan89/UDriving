package com.udriving.drivingapi.entity.activity;

/**
 * 活动状态常量
 */
public interface ActivitiStatusConstant {
    /**
     * 创建状态
     */
    byte create = 0;
    /**
     * 已经修改
     */
    byte already_modification = 1;
    /**
     * 发布
     */
    byte release = 2;
    /**
     * 完成
     */
    byte finish = 3;
    /**
     * 删除
     */
    byte delete = 4;
}
