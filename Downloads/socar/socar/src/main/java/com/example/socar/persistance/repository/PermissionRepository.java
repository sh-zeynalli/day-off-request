package com.example.socar.persistance.repository;

import com.example.socar.persistance.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Zeynalli on 05-Sep-19.
 */
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    List<Permission> findAllByEmployees_EmployeeId(Long id);

    List<Permission> findByToRole(Long id);

    @Modifying
    @Query(value = "update Permission p set p.status=:status, p.description=:description where p.id=:id")
    void  update (@Param("status") Long status, @Param("description") String description, @Param("id") Long id);



}
