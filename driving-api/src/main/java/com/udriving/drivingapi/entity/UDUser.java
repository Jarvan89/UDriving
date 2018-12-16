package com.udriving.drivingapi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Blob;

@Data
@Entity
public class UDUser {
    @Id
    @Column
    @GeneratedValue
    int id;
    @Column
    String openId;
    @Column
    int phone;
    @Column
    String nickname;
    @Column
    Blob integral;
    @Column
    String publishCommentList;
    @Column
    String joinActivityIdList;
    @Column
    String distinction;
    @Column
    int status;
}
