package com.gestion.formation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.formation.dto.FormationDTO;
import com.gestion.formation.entity.Formation;
import com.gestion.formation.entity.Planification;
import com.gestion.formation.mapper.FormationMapper;
import com.gestion.formation.repository.FormationRepository;
import com.gestion.formation.repository.PlanificationRepository;


import java.util.List;
import java.util.Optional;

@Service
public class FormationService {

    private final FormationRepository formationRepository;
    private final FormationMapper formationMapper;

    @Autowired
    private PlanificationRepository planificationRepository;

    public FormationService(FormationRepository formationRepository, FormationMapper formationMapper) {
        this.formationRepository = formationRepository;
        this.formationMapper = formationMapper;
    }

    public void createFormation(FormationDTO formationDTO){
        Optional<Formation> formation = Optional.ofNullable(formationMapper.convertToEntity(formationDTO));
        if (formation.isPresent()) {
            formationRepository.save(formation.get());
        }
    }

    public List<Formation> getAllFormations() {
        List<Formation> formations= formationRepository.findAll();
        formations.forEach(formation->{
            List<Planification> planifications = planificationRepository.findByFormation(formation);
            formation.setPlanifications(planifications);
        });
        return formations;
    }

    public List<Formation> findAllByIdIn(List<Long> formationIds) {
        return formationRepository.findAllById(formationIds);
    }

    public Formation findById(Long formationId) {
        return formationRepository.getReferenceById(formationId);
    }
}