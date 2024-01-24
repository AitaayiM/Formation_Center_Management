package com.gestion.formation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.formation.entity.Formation;
import com.gestion.formation.repository.FormationRepository;

@Service
public class FormationService {

    private final FormationRepository formationRepository;

    @Autowired
    public FormationService(FormationRepository formationRepository) {
        this.formationRepository = formationRepository;
    }

    public List<Formation> getAllFormations() {
        return formationRepository.findAll();
    }

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
    }
}

