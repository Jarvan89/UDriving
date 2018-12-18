package com.udriving.drivingapi.entity.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class UDEvaluateLable {
    @Id
    @Column
    @GeneratedValue
    int id;
    @Column
    String text;
    @Column
    String createUserId;
    @Column
    int status;
    @Column
    int showStatus;
}
