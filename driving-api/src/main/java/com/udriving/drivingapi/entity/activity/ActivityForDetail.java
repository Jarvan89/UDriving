package com.udriving.drivingapi.entity.activity;

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
    private String createUserId;
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
     * @param activity
     * @return
     */
    public static final ActivityForDetail convert(Activity activity) {
        if (activity == null) {
            return null;
        }
        ActivityForDetail activityForDetail = new ActivityForDetail();
        activityForDetail.setId(activity.getId());
        activityForDetail.setTitle(activity.getTitle());
        activityForDetail.setDistance(activity.getDistance());
        activityForDetail.setEstimateCost(activity.getEstimateCost());
        activityForDetail.setCreateUserName(activity.getCreateUserName());
        activityForDetail.setIntroducePicture(activity.getIntroducePicture());
        activityForDetail.setCreateUserId(activity.getCreateUserId());
        activityForDetail.setDepartAddressInfo(activity.getDepartAddressInfo());
        activityForDetail.setDestinationAddressInfo(activity.getDestinationAddressInfo());
        activityForDetail.setDepartTime(formatYYYYMMDDHHMMSS(activity.getDepartTimestamp()));
        activityForDetail.setBackTime(formatYYYYMMDDHHMMSS(activity.getBackTimestamp()));
        activityForDetail.setDays(countDays(activity.getBackTimestamp(), activity.getDepartTimestamp()));
        activityForDetail.setLookOverNumber(activity.getLookOverNumber());
        // TODO: 2019/1/5 查询用户信息逻辑待补充
//        activityForDetail.setMemberIdList(activity.getMemberIdList());
        activityForDetail.setWeChatFlockQrCode(activity.getWeChatFlockQrCode());
        activityForDetail.setCarNumber(activity.getCarNumber());
        activityForDetail.setPathImage(activity.getPathImage());
        activityForDetail.setNotes(activity.getNotes());
        return activityForDetail;
    }

}
