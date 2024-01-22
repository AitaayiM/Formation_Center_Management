package com.gestion.formation.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.gestion.formation.dto.FormationDTO;
import com.gestion.formation.entity.Formation;

@Component
public class FormationMapper {

    private static final ModelMapper modelMapper = new ModelMapper();
    
    public FormationDTO convertToDTO(Formation formation) {
        return modelMapper.map(formation, FormationDTO.class);
    }

    public Formation convertToEntity(FormationDTO formationDTO) {
        return modelMapper.map(formationDTO, Formation.class);
    }
}

