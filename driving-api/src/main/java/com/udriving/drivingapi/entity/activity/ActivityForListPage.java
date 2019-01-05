package com.udriving.drivingapi.entity.activity;

import lombok.Data;

import java.util.List;

import static com.udriving.drivingapi.util.DateUtil.countDays;
import static com.udriving.drivingapi.util.DateUtil.formatYYYYMMDDHHMMSS;

/**
 * 供列表页使用的活动实体类
 */
@Data
public class ActivityForListPage extends BaseActivity {
    /**
     * 创建人名
     */
    private String createUserName;
    /**
     * 活动天数
     */
    private short days;
    /**
     * 查看次数
     */
    private long lookOverNumber;
    /**
     * 活动宣传图片
     */
    private List<String> imageUrl;
    /**
     * 出发时间
     */
    private String departTime;
    /**
     * 返回时间
     */
    private String backTime;

    public static final ActivityForListPage convert(Activity activity, String createUserName) {
        if (activity == null) {
            return null;
        }
        ActivityForListPage activityForListPage = new ActivityForListPage();

        /**
         * 活动id
         */
        activityForListPage.setId(activity.getId());
        /**
         * 活动名
         */
        activityForListPage.setTitle(activity.getTitle());
        /**
         * 距离
         */
        activityForListPage.setDistance(activity.getDistance());
        /**
         * 预估费用
         */
        activityForListPage.setEstimateCost(activity.getEstimateCost());
        /**
         * 目的地城市
         */
        AddressInfo addressInfo = activity.getDestinationAddressInfo();
        if (addressInfo != null) {
            activityForListPage.setDestinationCity(addressInfo.getCity());
            activityForListPage.setDestinationProvince(addressInfo.getProvince());
        }
        addressInfo = activity.getDepartAddressInfo();
        if (addressInfo != null) {
            activityForListPage.setDepartCity(addressInfo.getCity());
        }
        /**
         * 创建人名
         */
        activityForListPage.setCreateUserName(createUserName);
        activityForListPage.setDays(countDays(activity.getBackTimestamp(), activity.getDepartTimestamp()));
        /**
         * 查看次数
         */
        activityForListPage.setLookOverNumber(activity.getLookOverNumber());
        /**
         * 活动宣传图片
         */
        activityForListPage.setImageUrl(activity.getIntroducePicture());
        /**
         *出发时间
         */
        activityForListPage.setDepartTime(formatYYYYMMDDHHMMSS(activity.getDepartTimestamp()));
        /**
         *返回时间
         */
        activityForListPage.setBackTime(formatYYYYMMDDHHMMSS(activity.getBackTimestamp()));
        return activityForListPage;
    }


}
