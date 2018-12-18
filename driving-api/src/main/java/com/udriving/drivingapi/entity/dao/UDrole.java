package com.udriving.drivingapi.entity.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/12/16
 */
@Data
public class UDrole {
    @Id
    @Column
    @GeneratedValue
    int id;
    @Column
    String userId;
    @Column
    String authorizedSigns;
}
