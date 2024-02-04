package com.gestion.formation.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestion.formation.dto.PlanificationDTO;
import com.gestion.formation.entity.Planification;

@Component
public class PlanificationMapper {

    @Autowired
    private ModelMapper modelMapper;
    
    public PlanificationDTO planificationToPlanificationDTO(Planification planification) {
        return modelMapper.map(planification, PlanificationDTO.class);
    }

    public Planification dtoToEntity(PlanificationDTO planificationDTO){
        Planification planification = modelMapper.map(planificationDTO, Planification.class);
        return planification;
    }
}
