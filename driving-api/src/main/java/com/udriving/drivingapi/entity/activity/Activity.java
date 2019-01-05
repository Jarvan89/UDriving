package com.udriving.drivingapi.entity.activity;


import com.udriving.drivingapi.util.JacksonUtil;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
public class Activity extends BaseActivity implements ActivitiStatusConstant {
    /**
     * 创建人用户id
     */
    @Column
    private String createUserId;
    /**
     * 活动介绍
     */
    @Column
    private String introduce;
    /**
     * 出发地信息
     */
    @Column
    private String departAddressInfo;
    /**
     * 成员列表
     */
    @Column
    private String memberIdList;
    /**
     * 目的地信息
     */
    @Column
    private String destinationAddressInfo;
    /**
     * 评论id列表
     */
    @Column
    private String commentIdList;
    /**
     * 途径地坐标
     */
    @Column
    private String approachCoordinate;
    /**
     * 活动介绍图片
     */
    @Column
    private String introducePicture;
    /**
     * 活动相册
     */
    @Column
    private String photoAlbum;
    /**
     * 出发时间
     */
    @Column
    private long departTimestamp;
    /**
     * 返回时间
     */
    @Column
    private long backTimestamp;
    /**
     * 微信群聊二维码
     */
    @Column
    private String weChatFlockQrCode;
    /**
     * 活动状态
     * 创建、审核通过、审核未通过
     */
    @Column
    private int status;
    /**
     * 参加活动的车辆id
     */
    @Column
    private String carNumber;
    /**
     * 路线图片
     */
    @Column
    private String pathImage;
    /**
     * 查看次数
     */
    @Column
    private long lookOverNumber;
    /**
     * 注意事项
     */
    @Column
    private String notes;

    /**
     * 获取活动介绍图片
     *
     * @return 活动介绍图片
     */
    public List<String> getIntroducePicture() {
        //图片名列表
        List<String> imageNameList = null;
        try {
            imageNameList = null;
            JacksonUtil.json2Bean(introducePicture, List.class);
        } catch (IOException e) {
            return null;
        }
        for (String imageName : imageNameList) {
            imageName = "https://liuhao.space/" + imageName;
        }
        return imageNameList;
    }

    /**
     * 设置活动介绍图片
     *
     * @param introducePicture 活动介绍图片链接列表
     */
    public void setIntroducePicture(List<String> introducePicture) {
        //图片名列表
        List<String> imageNameList = new LinkedList<>();
        //将客户端传上来的图片链接截取为仅保留图片文件名
        for (String imageUrl : introducePicture) {
            imageNameList.add(imageUrl.substring(imageUrl.lastIndexOf("/") + 1));
        }
        this.introducePicture = JacksonUtil.toJSONString(imageNameList);
    }

    /**
     * 获取目的地信息
     *
     * @return 目的地信息实体
     */
    public AddressInfo getDestinationAddressInfo() {
        try {
            return JacksonUtil.json2Bean(destinationAddressInfo, AddressInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 设置目的地信息
     *
     * @param destinationAddressInfo 目的地信息
     */
    public void setDestinationAddressInfo(AddressInfo destinationAddressInfo) {
        this.destinationAddressInfo = JacksonUtil.toJSONString(destinationAddressInfo);
    }

    /**
     * 获取出发地信息
     *
     * @return 出发地信息实体
     */
    public AddressInfo getDepartAddressInfo() {
        try {
            return JacksonUtil.json2Bean(departAddressInfo, AddressInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 设置出发地信息
     *
     * @param departAddressInfo 出发地信息实体
     */
    public void setDepartAddressInfo(AddressInfo departAddressInfo) {
        this.departAddressInfo = JacksonUtil.toJSONString(departAddressInfo);
    }

}
