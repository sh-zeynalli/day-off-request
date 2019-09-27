package com.example.socar.dto;

import com.example.socar.persistance.entity.Roles;

/**
 * Created by Zeynalli on 05-Sep-19.
 */
public class RolesDto {
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RolesDto() {

    }
    public RolesDto adapter(Roles r){
        RolesDto dto=new RolesDto(r.getId(), r.getName());
        return dto;
    }

    public RolesDto(Long roleId, String name) {

        this.roleId = roleId;
        this.name = name;
    }


    private Long roleId;

    public   String name;

}
