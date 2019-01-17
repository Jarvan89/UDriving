package com.udriving.drivingapi.entity.dao;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/12/16
 */
@Data
@Entity
public class UDrole {
    @Id
    @Column
    @GeneratedValue
    int id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userId",insertable = false, updatable = false)//设置在article表中的关联字段(外键)
    private UDUser user;//
    @Column
    String userId;
    @Column
    String role;

}
