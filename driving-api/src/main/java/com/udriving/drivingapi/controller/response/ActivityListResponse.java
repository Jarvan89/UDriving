package com.udriving.drivingapi.controller.response;

import com.udriving.drivingapi.entity.activity.Activity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 查询活动列表接口调用返回实体类
 */
@Getter
@Setter
@AllArgsConstructor
public class ActivityListResponse {
    /**
     * 活动列表
     */
    private List<Activity> activityList;

}
