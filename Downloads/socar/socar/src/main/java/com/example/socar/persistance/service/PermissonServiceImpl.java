package com.example.socar.persistance.service;

import com.example.socar.dto.PermissionDto;
import com.example.socar.persistance.entity.Employees;
import com.example.socar.persistance.entity.PType;
import com.example.socar.persistance.entity.Permission;
import com.example.socar.persistance.repository.EmployeeRepository;
import com.example.socar.persistance.repository.PermissionRepository;
import com.example.socar.persistance.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Zeynalli on 05-Sep-19.
 */
@Service
public class PermissonServiceImpl  implements PermissionService {

    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    TypeRepository typeRepository;
    @Override
    public List<Permission> findByE_id(Long id) {
        return permissionRepository.findAllByEmployees_EmployeeId(id);
    }

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission findById(Long id) {
        return permissionRepository.findById(id).get();
    }

    @Override
    public PermissionDto save(PermissionDto dto) {

        Employees e = employeeRepository.findById(dto.getEmployees().getId()).get();
        PType t = new PType(dto.getType().getT_id(), dto.getType().getName());
        Permission p = new Permission(
                e,
                dto.getDate(),
                dto.getReason(),
                t,
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getStartTime(),
                dto.getEndTime(),
                dto.getTo(),
                dto.getStatus(),
                dto.getDescription());
        p = permissionRepository.save(p);
        return  dto.adapter(p);
    }

    @Override
    public void update(Long id, Long status, String d) {
        permissionRepository.update(id,  d, status);
    }

    @Override
    public List<Permission> findByToRole(Long id) {
        return permissionRepository.findByToRole(id);
    }

    @Override
    public void deleteById(Long id) {
        permissionRepository.deleteById(id);
    }
}
