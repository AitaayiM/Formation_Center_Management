package com.gestion.formation.mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestion.formation.dto.FormateurExterneSignUpDTO;
import com.gestion.formation.entity.Formateur;

@Component
public class FormateurExterneMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Formateur mapToEntity(FormateurExterneSignUpDTO formateurExterneDTO) {
        return modelMapper.map(formateurExterneDTO, Formateur.class);
    }
}

