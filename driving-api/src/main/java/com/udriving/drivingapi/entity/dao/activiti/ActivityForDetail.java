package com.udriving.drivingapi.entity.dao.activiti;

import lombok.Data;

import java.util.List;

import static com.udriving.drivingapi.util.DateUtil.countDays;
import static com.udriving.drivingapi.util.DateUtil.formatYYYYMMDDHHMMSS;

/**
 * 供详情页使用的活动实体类
 */
@Data
public class ActivityForDetail extends BaseActivity {
    /**
     * 活动介绍图片
     */
    private List<String> introducePicture;
    /**
     * 创建人用户id
     */
    private long createUserId;
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
     * 出发时间
     */
    private String departTime;
    /**
     * 返回时间
     */
    private String backTime;
    /**
     * 活动天数
     */
    private short days;
    /**
     * 查看次数
     */
    private long lookOverNumber;
    /**
     * 成员列表
     */
    private List<String> memberIdList;
    /**
     * 微信群聊二维码
     */
    private String weChatFlockQrCode;
    /**
     * 参加活动的车辆id
     */
    private List<String> carNumber;
    /**
     * 路线图片
     */
    private String pathImage;
    /**
     * 注意事项
     */
    private String notes;
    /**
     * 审批意见
     */
    private String approveOpinion;

    /**
     * @param UDActivity
     * @return
     */
    public static final ActivityForDetail convert(UDActivity UDActivity) {
        if (UDActivity == null) {
            return null;
        }
        ActivityForDetail activityForDetail = new ActivityForDetail();
        activityForDetail.setId(UDActivity.getId());
        activityForDetail.setTitle(UDActivity.getTitle());
        activityForDetail.setDistance(UDActivity.getDistance());
        activityForDetail.setEstimateCost(UDActivity.getEstimateCost());
        activityForDetail.setCreateUserName(UDActivity.getCreateUserName());
        activityForDetail.setIntroducePicture(UDActivity.getIntroducePicture());
        activityForDetail.setCreateUserId(UDActivity.getCreateUserId());
        activityForDetail.setDepartAddressInfo(UDActivity.getDepartAddressInfo());
        activityForDetail.setDestinationAddressInfo(UDActivity.getDestinationAddressInfo());
        activityForDetail.setDepartTimestamp(UDActivity.getDepartTimestamp());
        activityForDetail.setBackTimestamp(UDActivity.getBackTimestamp());
        activityForDetail.setDepartTime(formatYYYYMMDDHHMMSS(UDActivity.getDepartTimestamp()));
        activityForDetail.setBackTime(formatYYYYMMDDHHMMSS(UDActivity.getBackTimestamp()));
        activityForDetail.setDays(countDays(UDActivity.getBackTimestamp(), UDActivity.getDepartTimestamp()));
        activityForDetail.setLookOverNumber(UDActivity.getLookOverNumber());
        // TODO: 2019/1/5 查询用户信息逻辑待补充
//        activityForDetail.setMemberIdList(UDActivity.getMemberIdList());
        activityForDetail.setWeChatFlockQrCode(UDActivity.getWeChatFlockQrCode());
        activityForDetail.setCarNumber(UDActivity.getCarNumber());
        activityForDetail.setPathImage(UDActivity.getPathImage());
        activityForDetail.setNotes(UDActivity.getNotes());
        activityForDetail.setApproveOpinion(UDActivity.getApproveOpinion());
        return activityForDetail;
    }

}
