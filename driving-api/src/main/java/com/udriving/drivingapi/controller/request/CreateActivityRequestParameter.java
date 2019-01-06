package com.udriving.drivingapi.controller.request;

import com.udriving.drivingapi.entity.activity.AddressInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 新建活动接口请求参数
 */
@Setter
@Getter
public class CreateActivityRequestParameter {
    /**
     * 活动名
     */
    private String title;
    /**
     * 活动介绍图片
     */
    private List<String> introducePicture;
    /**
     * 创建人用户id
     */
    private long createUserId;
    /**
     * 创建人昵称
     */
    private String createNikeName;
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
     * 预估费用
     */
    private float estimateCost;
    /**
     * 活动介绍
     */
    private String introduce;
    /**
     * 注意事项
     */
    private String notes;
    /**
     * 途径地坐标
     */
    private List<AddressInfo> approachCoordinate;
}
