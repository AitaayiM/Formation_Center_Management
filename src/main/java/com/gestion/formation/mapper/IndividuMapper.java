package com.gestion.formation.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestion.formation.dto.FormateurDTO;
import com.gestion.formation.dto.IndividuDTO;
import com.gestion.formation.entity.Individu;

@Component
public class IndividuMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public IndividuMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Individu convertToEntity(IndividuDTO individuDTO) {
        return modelMapper.map(individuDTO, Individu.class);
    }
}
