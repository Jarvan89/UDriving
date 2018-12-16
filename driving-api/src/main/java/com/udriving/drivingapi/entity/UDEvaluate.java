package com.udriving.drivingapi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class UDEvaluate {
    @Id
    @Column
    @GeneratedValue
    int id;
    @Column
    int starLevel;
    @Column
    String lableIdList;
    @Column
    int activityId;
    @Column
    int evaluateUserid;

}
