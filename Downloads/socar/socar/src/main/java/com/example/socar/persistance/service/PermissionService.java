package com.example.socar.persistance.service;

import com.example.socar.dto.PermissionDto;
import com.example.socar.persistance.entity.Permission;

import java.util.List;

/**
 * Created by Zeynalli on 05-Sep-19.
 */
public interface PermissionService {

    List<Permission> findByE_id(Long id);

    List<Permission> findAll();

    Permission findById(Long id);

    PermissionDto save(PermissionDto p);

    void update(Long id, Long s, String d);

    List<Permission> findByToRole(Long id);

    void deleteById(Long id);


}
