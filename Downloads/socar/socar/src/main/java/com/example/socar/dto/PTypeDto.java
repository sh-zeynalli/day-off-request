package com.example.socar.dto;

import com.example.socar.persistance.entity.PType;

/**
 * Created by Zeynalli on 05-Sep-19.
 */
public class PTypeDto {

    private Long t_id;

    private String name;

    public PTypeDto() {
    }

    public PTypeDto(Long t_id, String name) {
        this.t_id = t_id;
        this.name = name;
    }

    public Long getT_id() {

        return t_id;
    }

    public PTypeDto adapter(PType t){
        PTypeDto dto = new PTypeDto(t.getT_id(), t.getName());
        return dto;
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
