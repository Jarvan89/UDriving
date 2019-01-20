package com.udriving.drivingapi.entity.dao;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


import javax.persistence.*;
import java.util.Collection;


/**
 * @Coder shihaiyang
 * @Date 2019-01-20 22:28
 */

@Entity
@Table(name = "UD_Privilege")
@Data
public class UDPrivilege {




    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NonNull
    String name;

    @ManyToMany(mappedBy = "privileges")
    Collection<UDRole> roles;

}
