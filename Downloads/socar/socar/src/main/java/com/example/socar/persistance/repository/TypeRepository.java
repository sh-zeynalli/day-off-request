package com.example.socar.persistance.repository;

import com.example.socar.persistance.entity.PType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Zeynalli on 05-Sep-19.
 */
public interface TypeRepository extends JpaRepository<PType, Long> {
}
