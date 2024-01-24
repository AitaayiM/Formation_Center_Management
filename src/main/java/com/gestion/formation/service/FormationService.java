package com.gestion.formation.service;

import org.springframework.stereotype.Service;

import com.gestion.formation.dto.FormationDTO;
import com.gestion.formation.entity.Formation;
import com.gestion.formation.mapper.FormationMapper;
import com.gestion.formation.repository.FormationRepository;

import java.util.List;
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

    public List<Formation> getAllFormations() {
        return formationRepository.findAll();
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
}