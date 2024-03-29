package com.example.socar.persistance.service;

import com.example.socar.dto.EmployeesDto;
import com.example.socar.dto.RolesDto;
import com.example.socar.persistance.entity.Employees;
import com.example.socar.persistance.entity.Roles;
import com.example.socar.persistance.repository.EmployeeRepository;
import com.example.socar.persistance.repository.RolesRepository;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    RolesRepository rolesRepository;

    @Override
    public Employees findUserById(Long id) {
        return employeeRepository.findById(id).get();
    }

    public List<Roles> findRoleByEId(Long id) {
        return rolesRepository.getByEmployees_EmployeeId(id);
    }

    @Override
    public Employees findByMail(String mail) {
        return employeeRepository.findByMail(mail);
    }

    @Override
    public List<Employees> getByRoleId(Long id) {
        return employeeRepository.getByRoles_roleId(id);
    }

    @Override
    public Employees findbyPermissionId(Long id) {
        return employeeRepository.findByPermission_id(id);
    }

    @Override
    public Employees save(Employees dto) {
        return employeeRepository.save(dto);
    }

    @Override
    public void addRole(Long id, Long roles) {
        employeeRepository.addRole( id, roles);
    }

    @Override
    public List<EmployeesDto> findAllEmployees() {
        List<Employees> list = employeeRepository.findAll();
        List<EmployeesDto> dto = list.stream().map(e -> new EmployeesDto(
                e.getId(),
                e.getFullname(),
                e.getMail(),
                e.getPassword()
        )).collect(Collectors.toList());
        return dto;
    }

}
