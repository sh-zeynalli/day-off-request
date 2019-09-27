package com.example.socar.persistance.repository;

import com.example.socar.persistance.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employees, Long> {
    Employees findByMailAndPassword(String mail, String password);
    Employees findByMail(String mail);
    List<Employees> getByRoles_roleId(Long id);
    List<Employees> findByRoles(Long id);

    Employees findByPermission_id(Long id);


}
