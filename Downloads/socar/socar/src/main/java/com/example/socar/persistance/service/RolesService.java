package com.example.socar.persistance.service;

import com.example.socar.dto.RolesDto;
import com.example.socar.persistance.entity.Roles;

import java.util.List;

public interface RolesService {

    public List<Roles> findAllRoles();

    List<Roles> getRolesbyEId(Long id);
}
