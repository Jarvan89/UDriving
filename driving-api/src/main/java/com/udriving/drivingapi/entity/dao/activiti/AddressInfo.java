package com.udriving.drivingapi.entity.dao.activiti;

import lombok.Data;

/**
 * 地址信息实体
 */
@Data
public class AddressInfo {
    /**
     * 经度
     */
    private float longitude;
    /**
     * 纬度
     */
    private float latitude;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区县
     */
    private String county;
    /**
     * 完整地址
     */
    private String address;
}
