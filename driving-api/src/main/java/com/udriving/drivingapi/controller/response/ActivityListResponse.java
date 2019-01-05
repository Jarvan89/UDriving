package com.udriving.drivingapi.controller.response;

import com.udriving.drivingapi.entity.activity.Activity;
import com.udriving.drivingapi.entity.activity.ActivityForListPage;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

/**
 * 查询活动列表接口调用返回实体类
 */
@Getter
@Setter
public class ActivityListResponse {
    /**
     * 活动列表
     */
    private List<ActivityForListPage> activityList;

    /**
     * @param pActivityList
     */
    public ActivityListResponse(List<Activity> pActivityList) {
        if (pActivityList != null) {
            activityList = new LinkedList<>();
            for (Activity activity : pActivityList) {
                activityList.add(ActivityForListPage.convert(activity, "名字尚未确定"));
            }
        }
    }
}
