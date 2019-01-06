package com.udriving.drivingapi.controller.request;

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
    private Integer id;
    /**
     * 审批意见
     */
    private String approveOpinion;
}
