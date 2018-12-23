package com.udriving.drivingapi.controller.request;

import lombok.Data;

/**
 * 新建活动接口请求参数
 */
@Data
public class CreateActivityRequestParameter {
    /**
     * 创建人用户id
     */
    private String createUserId;
    /**
     * 活动名
     */
    private String name;
    /**
     * 活动介绍
     */
    private String introduce;
    /**
     * 出发点纬度
     */
    private float departLatitude;
    /**
     * 出发点经度
     */
    private float departLongitude;

    /**
     * 目的地纬度
     */
    private float destinationLatitude;
    /**
     * 目的地经度
     */
    private float destinationLongitude;
    /**
     * 预估费用
     */
    private float estimateCost;
    /**
     * 活动介绍图片
     */
    private String introducePicture;
    /**
     * 出发时间
     */
    private long departTimestamp;
    /**
     * 返回时间
     */
    private long backTimestamp;
    /**
     * 微信群聊二维码
     */
    private String weChatFlockQrCode;
}
