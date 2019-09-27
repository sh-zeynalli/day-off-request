package com.example.socar.persistance.service;

import com.example.socar.dto.PTypeDto;
import com.example.socar.persistance.entity.PType;
import com.example.socar.persistance.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Zeynalli on 05-Sep-19.
 */
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    TypeRepository typeRepository;

    @Override
    public List<PTypeDto> findAllTypes() {

        List<PType> list=typeRepository.findAll();
        List<PTypeDto> listDto=list.stream().map(t -> new PTypeDto(
                t.getT_id(),
                t.getName()
        )).collect(Collectors.toList());
        return listDto;
    }

    @Override
    public PTypeDto findById(Long id) {
        PType t = typeRepository.findById(id).get();
        PTypeDto dto = new PTypeDto(t.getT_id(), t.getName());
        return dto;
    }
}
