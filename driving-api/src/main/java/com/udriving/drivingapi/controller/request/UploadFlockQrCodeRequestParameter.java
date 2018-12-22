package com.udriving.drivingapi.controller.request;

import lombok.Data;

/**
 * 上传群二维码请求参数
 */
@Data
public class UploadFlockQrCodeRequestParameter {
    /**
     * 活动id
     */
    private Integer acitivityId;

    /**
     * 二维码文件的Base64编码字符串
     */
    private String qrCodeBase64Code;
}
