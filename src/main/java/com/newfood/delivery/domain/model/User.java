package com.newfood.delivery.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(name = "users_groups", joinColumns = @JoinColumn(name = "users_id"), inverseJoinColumns = @JoinColumn(name = "groups_id"))
    private List<Group> groups = new ArrayList<>();

    public boolean samePassword(String password) {
        return getPassword().equals(password);
    }

    public boolean unequalPassword(String password) {
        return !samePassword(password);
    }
}
