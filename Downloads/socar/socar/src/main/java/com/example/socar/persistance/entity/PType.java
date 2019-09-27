package com.example.socar.persistance.entity;

import javax.persistence.*;

/**
 * Created by Zeynalli on 05-Sep-19.
 */
@Entity
@Table(name = "type")
public class PType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long t_id;

    @Override
    public String toString() {
        return "PType{" +
                "t_id=" + t_id +
                ", name='" + name + '\'' +
                '}';
    }

    private String name;

    public PType() {
    }

    public PType(Long t_id, String name) {

        this.t_id = t_id;
        this.name = name;
    }

    public Long getT_id() {

        return t_id;
    }

    public void setT_id(Long t_id) {
        this.t_id = t_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
