package com.gestion.formation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.formation.dto.PlanificationDTO;
import com.gestion.formation.entity.Entreprise;
import com.gestion.formation.entity.Formateur;
import com.gestion.formation.entity.Formation;
import com.gestion.formation.entity.Planification;
import com.gestion.formation.mapper.PlanificationMapper;
import com.gestion.formation.repository.EntrepriseRepository;
import com.gestion.formation.repository.FormateurRepository;
import com.gestion.formation.repository.FormationRepository;
import com.gestion.formation.repository.PlanificationRepository;
import com.gestion.formation.util.Validator;

@Service
public class PlanificationService {

    @Autowired
    private PlanificationRepository planificationRepository;
    @Autowired
    private FormationRepository formationRepository;
    @Autowired
    private FormateurRepository formateurRepository;
    @Autowired
    private EntrepriseRepository entrepriseRepository;
    @Autowired
    private PlanificationMapper planificationMapper;


    public void planifierFormation(PlanificationDTO planification) {
        // Assurez-vous que les formations, formateurs et entreprises existent avant de les associer
        Formation formation = formationRepository.findById(planification.getFormation().getId())
                .orElseThrow(() -> new RuntimeException("Formation non trouvée"));

        Formateur formateur = formateurRepository.findById(planification.getFormateur().getId())
                .orElseThrow(() -> new RuntimeException("Formateur non trouvé"));

        Entreprise entreprise = entrepriseRepository.findById(planification.getEntreprise().getId())
                .orElseThrow(() -> new RuntimeException("Entreprise non trouvée"));

        if (planification.getDateDebut() == null || planification.getDateFin() == null) {
            throw new IllegalArgumentException("Tous les champs doivent être renseignés");
        }else if(!Validator.isDateInTheFuture(planification.getDateDebut(), planification.getDateFin())){
            throw new IllegalArgumentException("Les dates de début et de fin doivent être dans le futur ou présentes");
        }else if(!Validator.isGreaterThan(planification.getDateDebut(), planification.getDateFin())){
            throw new IllegalArgumentException("La date de fin doit être supérieure à la date de début");
        }

        Planification planificationToSave = planificationMapper.dtoToEntity(planification);
        planificationToSave.setFormation(formation);
        planificationToSave.setFormateur(formateur);
        planificationToSave.setEntreprise(entreprise);

        planificationRepository.save(planificationToSave);
    }

    public List<Planification> getListePlanifications() {
        return planificationRepository.findAll();
    }
}

