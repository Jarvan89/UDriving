package com.udriving.drivingapi.http.controller.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 拒绝审批活动接口请求参数
 */
@Setter
@Getter
public class RefuseApprovalActivityRequestParameter {
    /**
     * 活动id
     */
    private Long id;
    /**
     * 审批意见
     */
    private String approveOpinion;
}
