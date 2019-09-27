package com.example.socar.persistance.service;

import com.example.socar.dto.RolesDto;
import com.example.socar.persistance.entity.Roles;
import com.example.socar.persistance.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    RolesRepository rolesRepository;

    @Override
    public List<Roles> findAllRoles() {
        List<Roles> list=rolesRepository.findAll();
//        List<RolesDto> dtoList = list.stream().map(r -> new RolesDto(
//                r.getId(),
//                r.getName()
//        )).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<Roles> getRolesbyEId(Long id) {
        return rolesRepository.getByEmployees_EmployeeId(id);
    }


}
