package com.example.socar.persistance.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long roleId;

    public  String name;

    @ManyToMany(mappedBy = "roles")
    private Set<Employees> employees = new HashSet<>();

    public Roles(String name) {
        this.name = name;
    }

    public Roles() {

    }

    public Long getId() {

        return roleId;
    }

    public void setId(Long id) {
        this.roleId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
