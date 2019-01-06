package com.udriving.drivingapi.controller.request;

import com.udriving.drivingapi.entity.activity.AddressInfo;
import lombok.Data;

import java.util.List;

/**
 * 修改活动接口请求参数
 */
@Data
public class MoidfyActivityRequestParameter {
    /**
     * 活动id
     */
    protected Long id;
    /**
     * 活动名
     */
    protected String title;
    /**
     * 预估费用
     */
    protected float estimateCost;
    /**
     * 创建人用户名，该名仅针对本次活动有效
     */
    private String createUserName;
    /**
     * 出发地信息
     */
    private AddressInfo departAddressInfo;
    /**
     * 目的地信息
     */
    private AddressInfo destinationAddressInfo;
    /**
     * 出发时间
     */
    private long departTimestamp;
    /**
     * 返回时间
     */
    private long backTimestamp;
    /**
     * 成员列表
     */
    private List<String> memberIdList;
    /**
     * 参加活动的车辆id
     */
    private List<String> carNumber;
    /**
     * 注意事项
     */
    private String notes;
}
