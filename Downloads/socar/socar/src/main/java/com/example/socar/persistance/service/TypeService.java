package com.example.socar.persistance.service;

import com.example.socar.dto.PTypeDto;

import java.util.List;

/**
 * Created by Zeynalli on 05-Sep-19.
 */
public interface TypeService {

public List<PTypeDto> findAllTypes();

public PTypeDto findById(Long id);
}
