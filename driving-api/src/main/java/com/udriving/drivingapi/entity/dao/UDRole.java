package com.udriving.drivingapi.entity.dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/12/16
 */
@Data
@Entity
@Table(name = "UD_Role")
@RequiredArgsConstructor
@NoArgsConstructor
public class UDRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NonNull
    String name;
    @ManyToMany(mappedBy = "roles")
    Collection<UDUser> users;
    @ManyToMany
    @JoinTable(
            name = "ud_roles_ud_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private Collection<UDPrivilege> privileges;

}
