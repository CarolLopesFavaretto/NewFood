package com.newfood.delivery.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(name = "groups_permission", joinColumns = @JoinColumn(name = "groups_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions = new HashSet<>();

    public Boolean removePermissions(Permission permission) {
        return getPermissions().remove(permission);
    }

    public Boolean addPermissions(Permission permission) {
        return getPermissions().add(permission);
    }
}
