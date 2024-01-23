package com.gestion.formation.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.gestion.formation.dto.FormateurDTO;
import com.gestion.formation.entity.Formateur;

@Component
public class FormateurMapper {

    private final ModelMapper modelMapper;

    public FormateurMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Formateur convertToEntity(FormateurDTO formateurDTO) {
        return modelMapper.map(formateurDTO, Formateur.class);
    }
}

