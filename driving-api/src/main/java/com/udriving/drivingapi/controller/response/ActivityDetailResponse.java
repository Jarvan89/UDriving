package com.udriving.drivingapi.controller.response;

import com.udriving.drivingapi.entity.activity.ActivityForDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 查询活动列表接口调用返回实体类
 */
@Getter
@AllArgsConstructor
public class ActivityDetailResponse {
    /**
     * 活动详情
     */
    private ActivityForDetail activityDetail;
}
