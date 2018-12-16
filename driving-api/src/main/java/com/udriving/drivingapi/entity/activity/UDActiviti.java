package com.udriving.drivingapi.entity.activity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class UDActiviti implements ActivitiStatusConstant {

    /**
     * 活动id
     */
    @Id
    @GeneratedValue
    int id;
    /**
     * 创建人用户id
     */
    @Column
    String createUserId;
    /**
     *活动名
     */
    @Column
    String name;
    /**
     *活动介绍
     */
    @Column
    String introduce;
    /**
     *出发点纬度
     */
    @Column
    float departLatitude;
    /**
     *出发点经度
     */
    @Column
    float departLongitude;
    /**
     *成员列表
     */
    @Column
    String memberIdList;
    /**
     *目的地纬度
     */
    @Column
    float destinationLatitude;
    /**
     *目的地经度
     */
    @Column
    float destinationLongitude;
    /**
     *评论id列表
     */
    @Column
    String commentIdList;
    /**
     *途径地坐标
     */
    @Column
    String approachCoordinate;
    /**
     *预估费用
     */
    @Column
    float estimateCost;
    /**
     *活动介绍图片
     */
    @Column
    String introducePicture;
    /**
     *活动相册
     */
    @Column
    String photoAlbum;
    /**
     *出发时间
     */
    @Column
    long departTimestamp;
    /**
     *返回时间
     */
    @Column
    long backTimestamp;
    /**
     *微信群聊二维码
     */
    @Column
    String weChatFlockQrCode;
    /**
     *活动状态
     * 创建、审核通过、审核未通过
     */
    @Column
    int status;
    /**
     *参加活动的车辆id
     */
    @Column
    String carNumber;
    /**
     *路线图片
     */
    @Column
    String pathImage;
}
