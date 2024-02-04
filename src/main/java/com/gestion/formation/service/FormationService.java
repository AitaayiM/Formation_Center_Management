package com.gestion.formation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.formation.dto.FormationDTO;
import com.gestion.formation.entity.Formation;
import com.gestion.formation.entity.Planification;
import com.gestion.formation.mapper.FormationMapper;
import com.gestion.formation.repository.FormationRepository;
import com.gestion.formation.repository.PlanificationRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
 
    public List<Formation> getFormationsWithFilters(String categorie, String ville, Date date) {
    if (categorie != null && !categorie.isEmpty()) {
        return formationRepository.findByCategorie(categorie);
    } else if (ville != null && !ville.isEmpty()) {
        return formationRepository.findByVille(ville);
    } else if (date != null) {
        List<Planification> planifications = planificationRepository.findByDateDebut(date)
                .orElseThrow(() -> new EntityNotFoundException("Formation non trouvée avec la date : " + date));
        System.out.print(planifications);
        List<Formation> formations = planifications.stream()
                .map(Planification::getFormation)
                .collect(Collectors.toList());
        return formations;
    } else {
        return formationRepository.findAll();
    }


    }
  /* 
    public List<Formation> rechercherFormations(String categorie, String ville, String date) {
        if (categorie != null && ville != null && date != null) {
            return formationRepository.findByCategorieAndVilleAndDate(categorie, ville, date);
        } else if (categorie != null && ville != null) {
            return formationRepository.findByCategorieAndVille(categorie, ville);
        } else if (categorie != null && date != null) {
            return formationRepository.findByCategorieAndDate(categorie, date);
        } else if (ville != null && date != null) {
            return formationRepository.findByVilleAndDate(ville, date);
        } else if (categorie != null) {
            return formationRepository.findByCategorie(categorie);
        } else if (ville != null) {
            return formationRepository.findByVille(ville);
        } else if (date != null) {
            return formationRepository.findByDate(date);
        } else {
            return formationRepository.findAll();
        }
    }*/

    public List<Formation> findAllByIdIn(List<Long> formationIds) {
        return formationRepository.findAllById(formationIds);
    }

    public Formation findById(Long formationId) {
        return formationRepository.getReferenceById(formationId);
    }
}