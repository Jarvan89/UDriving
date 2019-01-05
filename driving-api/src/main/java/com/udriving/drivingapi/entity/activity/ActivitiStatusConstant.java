package com.udriving.drivingapi.entity.activity;

/**
 * 活动状态常量
 */
public interface ActivitiStatusConstant {
    /**
     * 创建状态
     */
    byte CREATE = 0;
    /**
     * 已经修改
     */
    byte WAITAUDIT = 1;
    /**
     * 发布
     */
    byte RELEASE = 2;
    /**
     * 审核拒绝
     */
    byte REFUSE = 3;
    /**
     * 完成
     */
    byte FINISH = 4;
    /**
     * 删除
     */
    byte DELETE = 6;
}
