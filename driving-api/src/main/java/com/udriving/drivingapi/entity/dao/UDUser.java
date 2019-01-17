package com.udriving.drivingapi.entity.dao;

import lombok.Cleanup;
import lombok.Data;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;

@Data
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

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    //级联保存、更新、删除、刷新;延迟加载。当删除用户，会级联删除该用户的所有文章
            //拥有mappedBy注解的实体类为关系被维护端
            List<UDrole> role;

    @Column
    @ColumnTransformer(
            read = "decrypt(password)",
            write = "encrypt(nvl(?, 'null'))"
    )
    private String password;
}
