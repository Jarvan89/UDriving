package com.udriving.drivingapi.entity.dao.activiti;

import lombok.Data;

import javax.persistence.Column;
import java.util.List;

import static com.udriving.drivingapi.util.DateUtil.countDays;
import static com.udriving.drivingapi.util.DateUtil.formatYYYYMMDDHHMMSS;

/**
 * 供列表页使用的活动实体类
 */
@Data
public class ActivityForListPage extends BaseActivity {
    /**
     * 活动天数
     */
    private short days;
    /**
     * 查看次数
     */
    private long lookOverNumber;
    /**
     * 目的地城市
     */
    @Column
    protected String destinationCity;
    /**
     * 出发时间
     */
    private String departTime;
    /**
     * 返回时间
     */
    private String backTime;
    /**
     * 目的地省份
     */

    private String destinationProvince;
    /**
     * 出发地城市
     */
    private String departCity;
    /**
     * 活动介绍图片
     */
    private List<String> introducePicture;

    public static final ActivityForListPage convert(UDActivity UDActivity) {
        if (UDActivity == null) {
            return null;
        }
        ActivityForListPage activityForListPage = new ActivityForListPage();
        activityForListPage.setId(UDActivity.getId());
        activityForListPage.setTitle(UDActivity.getTitle());
        activityForListPage.setDistance(UDActivity.getDistance());
        activityForListPage.setEstimateCost(UDActivity.getEstimateCost());
        //地址信息，此处取的是目的地地址信息
        AddressInfo addressInfo = UDActivity.getDestinationAddressInfo();
        if (addressInfo != null) {
            activityForListPage.setDestinationCity(addressInfo.getCity());
            activityForListPage.setDestinationProvince(addressInfo.getProvince());
        }
        //此处取的是出发地地址信息
        addressInfo = UDActivity.getDepartAddressInfo();
        if (addressInfo != null) {
            activityForListPage.setDepartCity(addressInfo.getCity());
        }
        activityForListPage.setCreateUserName(UDActivity.getCreateUserName());
        activityForListPage.setDays(countDays(UDActivity.getBackTimestamp(), UDActivity.getDepartTimestamp()));
        activityForListPage.setLookOverNumber(UDActivity.getLookOverNumber());
        activityForListPage.setIntroducePicture(UDActivity.getIntroducePicture());
        activityForListPage.setDepartTime(formatYYYYMMDDHHMMSS(UDActivity.getDepartTimestamp()));
        activityForListPage.setBackTime(formatYYYYMMDDHHMMSS(UDActivity.getBackTimestamp()));
        return activityForListPage;
    }
}
