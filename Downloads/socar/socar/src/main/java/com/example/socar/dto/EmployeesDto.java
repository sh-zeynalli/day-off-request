package com.example.socar.dto;

import com.example.socar.persistance.entity.Employees;
import com.example.socar.persistance.entity.Roles;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by Zeynalli on 05-Sep-19.
 */
public class EmployeesDto {

    private Long employeeId;

    Set<RolesDto> roles = new HashSet<>();


    private String fullname;

    private String mail;

    private String password;

    private Set<PermissionDto> permission;

    public EmployeesDto() {
    }

    public EmployeesDto(Long employeeId, Set<RolesDto> roles, String fullname, String mail, String password) {
        this.employeeId = employeeId;
        this.roles = roles;
        this.fullname = fullname;
        this.mail = mail;
        this.password = password;

    }

    public EmployeesDto(Long employeeId, String fullname, String mail, String password) {
        this.employeeId = employeeId;
        this.fullname = fullname;
        this.mail = mail;
        this.password = password;

    }

    public EmployeesDto adapter(Employees e){

        RolesDto r = new RolesDto();
        Set<RolesDto> rolesDtoSet = new HashSet<>();
        rolesDtoSet.add(r.adapter(e.getRoles().iterator().next()));

        EmployeesDto dto=new EmployeesDto(e.getId(),
                rolesDtoSet, e.getFullname(),
                e.getMail(),e.getPassword());
        return dto;
    }

    public Long getId() {

        return employeeId;
    }

    public void setId(Long id) {
        this.employeeId = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Set<RolesDto> getRoles() {
        return roles;
    }



    public void setRoles(Set<RolesDto> roles) {
        this.roles = roles;
    }

    public Set<PermissionDto> getPermission() {
        return permission;
    }

    public void setPermission(Set<PermissionDto> permission) {
        this.permission = permission;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
