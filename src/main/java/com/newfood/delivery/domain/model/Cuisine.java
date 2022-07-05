package com.newfood.delivery.domain.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "cuisine")
public class Cuisine {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cuisine)) return false;
        Cuisine cuisine = (Cuisine) o;
        return Objects.equals(id, cuisine.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
