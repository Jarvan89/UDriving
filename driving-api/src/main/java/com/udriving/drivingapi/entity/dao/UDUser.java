package com.udriving.drivingapi.entity.dao;

import lombok.Data;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Collection;
import java.util.List;

@Data
@Table(name = "UD_User")
@Entity
public class UDUser {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
            long id;
    @Column
    String userId;
    @Column
    String openId;
    @Column
    int phone;
    @Column
    String nickname;
    //    @Column
//    Blob integral;
//    @Column
//    String publishCommentList;
//    @Column
//    String joinActivityIdList;
    @Column
    String distinction;
    @Column
    private boolean enabled;
    @Column
    @ColumnTransformer(
            read = "decrypt(password)",
            write = "encrypt(ifnull(?, 'null'))"
    )
    private String password;


    @ManyToMany
    @JoinTable(
            name = "ud_users_ud_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<UDRole> roles;

}
