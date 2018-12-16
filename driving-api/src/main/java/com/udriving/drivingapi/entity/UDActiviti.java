package com.udriving.drivingapi.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class UDActiviti {
    @Id
    @GeneratedValue
    int id;
    @Column
    String createUserId;
    @Column
    String name;
    @Column
    String introduce;
    @Column
    float departLatitude;
    @Column
    float departLongitude;
    @Column
    String memberIdList;
    @Column
    float destinationLatitude;
    @Column
    float destinationLongitude;
    @Column
    String commentIdList;
    @Column
    String approachCoordinate;
    @Column
    float estimateCost;
    @Column
    String introducePicture;
    @Column
    String photoAlbum;
    @Column
    Date departTimestamp;
    @Column
    Date backTimestamp;
    @Column
    String weChatFlockQrCode;
    @Column
    int status;
    @Column
    String carNumber;
    @Column
    String pathImage;
}
