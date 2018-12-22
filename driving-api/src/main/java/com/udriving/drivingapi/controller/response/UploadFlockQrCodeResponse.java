package com.udriving.drivingapi.controller.response;

import lombok.Data;

/**
 * 上传群二维码返回结果
 */
@Data
public class UploadFlockQrCodeResponse {
    /**
     * 二维码文件名（不含存储路径信息，用于直接存储进数据库）
     */
    private String fileName;

    /**
     * 二维码文件url（含有存储路径信息，用于前端随时显示使用。不需要写入数据库）
     */
    private String fileUrl;
}
