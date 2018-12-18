package com.udriving.drivingapi.entity.dao;

import lombok.Cleanup;
import lombok.Data;
import org.hibernate.annotations.ColumnTransformer;

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
    String userId;
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

    @Column
    @ColumnTransformer(
            read = "decrypt(password)",
            write = "encrypt(nvl(?, 'null'))"
    )
    private String password;
}
