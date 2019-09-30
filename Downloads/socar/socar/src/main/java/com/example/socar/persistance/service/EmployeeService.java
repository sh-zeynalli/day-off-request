package com.example.socar.persistance.service;

import com.example.socar.dto.EmployeesDto;
import com.example.socar.dto.RolesDto;
import com.example.socar.persistance.entity.Employees;
import com.example.socar.persistance.entity.Roles;

import java.util.List;
import java.util.Set;

public interface EmployeeService {


    public Employees findUserById(Long id);

    public List<EmployeesDto> findAllEmployees();

    public List<Roles> findRoleByEId(Long id);

    public Employees findByMail(String mail);

    public List<Employees> getByRoleId(Long id);

    public Employees findbyPermissionId(Long id);

    Employees save(Employees dto);

    void addRole(Long id, Long roles);

}
