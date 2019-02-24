package com.udriving.drivingapi.http.pojo.activity;

import com.udriving.drivingapi.entity.dao.activiti.AddressInfo;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2019/2/24
 */
@Data
@ToString
public class ReqActivity {
    /**
     * 活动id
     */
    protected Long id;
    /**
     * 活动名
     */
    @NotBlank(message="活动名不能为空")
    private String title;
    /**
     * 预估费用
     */
    protected long estimateCost;
    /**
     * 活动介绍图片地址
     */
    private List<String> introducePicture;
    /**
     * 创建人用户id
     */
    private long createUserId;
    /**
     * 创建人昵称活动名称可以和 userID 名称不一致
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
     * 活动介绍
     */
    private String introduce;
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
    /**
     * 途径地坐标
     */
    private List<AddressInfo> approachCoordinate;
}
