package com.udriving.drivingapi.entity.weichat;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Coder shihaiyang
 * @Date 2019-01-07 10:38
 */
@Data
public class WeiRegInfo {

    @NotBlank(message="code不能为空")
    String code;
    String chatName;
}
