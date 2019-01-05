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

    public static final ActivityForListPage convert(Activity activity) {
        if (activity == null) {
            return null;
        }
        ActivityForListPage activityForListPage = new ActivityForListPage();
        activityForListPage.setId(activity.getId());
        activityForListPage.setTitle(activity.getTitle());
        activityForListPage.setDistance(activity.getDistance());
        activityForListPage.setEstimateCost(activity.getEstimateCost());
        //地址信息，此处取的是目的地地址信息
        AddressInfo addressInfo = activity.getDestinationAddressInfo();
        if (addressInfo != null) {
            activityForListPage.setDestinationCity(addressInfo.getCity());
            activityForListPage.setDestinationProvince(addressInfo.getProvince());
        }
        //此处取的是出发地地址信息
        addressInfo = activity.getDepartAddressInfo();
        if (addressInfo != null) {
            activityForListPage.setDepartCity(addressInfo.getCity());
        }
        activityForListPage.setCreateUserName(activity.getCreateUserName());
        activityForListPage.setDays(countDays(activity.getBackTimestamp(), activity.getDepartTimestamp()));
        activityForListPage.setLookOverNumber(activity.getLookOverNumber());
        activityForListPage.setImageUrl(activity.getIntroducePicture());
        activityForListPage.setDepartTime(formatYYYYMMDDHHMMSS(activity.getDepartTimestamp()));
        activityForListPage.setBackTime(formatYYYYMMDDHHMMSS(activity.getBackTimestamp()));
        return activityForListPage;
    }


}
