package com.example.socar.persistance.repository;

import com.example.socar.persistance.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RolesRepository extends JpaRepository<Roles, Long> {

List<Roles> getByEmployees_EmployeeId(Long employeeId);
}
