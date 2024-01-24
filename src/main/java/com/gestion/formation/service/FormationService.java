package com.gestion.formation.service;

import org.springframework.stereotype.Service;

import com.gestion.formation.dto.FormationDTO;
import com.gestion.formation.entity.Formation;
import com.gestion.formation.mapper.FormationMapper;
import com.gestion.formation.repository.FormationRepository;

import java.util.Optional;

@Service
public class FormationService {

    private final FormationRepository formationRepository;
    private final FormationMapper formationMapper;

    public FormationService(FormationRepository formationRepository, FormationMapper formationMapper) {
        this.formationRepository = formationRepository;
        this.formationMapper = formationMapper;
    }

    public void createFormation(FormationDTO formationDTO) {

        Optional<Formation> formation = Optional.ofNullable(formationMapper.convertToEntity(formationDTO));
        if (formation.isPresent()) {
            formationRepository.save(formation.get());
        }
    }
}

