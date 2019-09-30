package com.example.socar.persistance.repository;

import com.example.socar.persistance.entity.Employees;
import com.example.socar.persistance.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface EmployeeRepository extends JpaRepository<Employees, Long> {
    Employees findByMailAndPassword(String mail, String password);
    Employees findByMail(String mail);
    List<Employees> getByRoles_roleId(Long id);
    List<Employees> findByRoles(Long id);

    Employees findByPermission_id(Long id);

    @Modifying
    @Query(value = "insert into authorities values (?, ?)", nativeQuery = true)
    void  addRole (@Param("id") Long id, @Param("roles") Long role);

}
