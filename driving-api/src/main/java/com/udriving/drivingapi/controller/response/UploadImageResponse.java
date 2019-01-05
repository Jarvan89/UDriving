package com.udriving.drivingapi.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 上传群图片返回结果
 */
@Getter
@AllArgsConstructor
public class UploadImageResponse {
    /**
     * 二维码文件url（含有存储路径信息，用于前端随时显示使用。不需要写入数据库）
     */
    private String fileUrl;
}
