package com.udriving.drivingapi.http.pojo.activity;

import lombok.Data;

/**
 * 修改活动请求 request
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2019/2/24
 */
@Data
public class HttpModifyActivity {
    Long Id;
    String key;
    String value;
}
