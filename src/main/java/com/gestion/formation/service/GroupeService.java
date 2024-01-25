package com.gestion.formation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.formation.entity.Formateur;
import com.gestion.formation.entity.Formation;
import com.gestion.formation.entity.Individu;
import com.gestion.formation.entity.Groupe;
import com.gestion.formation.repository.FormateurRepository;
import com.gestion.formation.repository.FormationRepository;
import com.gestion.formation.repository.GroupeRepository;
import com.gestion.formation.repository.IndividuRepository;
import com.gestion.formation.util.Validator;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GroupeService {

    @Autowired
    private GroupeRepository groupeRepository;

    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    private FormateurRepository formateurRepository;

    @Autowired
    private IndividuRepository individuRepository;

    public void inscrireIndividuAFormation(Groupe groupe, List<Long> individuIds) {
        // Créez un groupe pour la formation si nécessaire
        Formation formation = formationRepository.findById(groupe.getFormation().getId())
	                .orElseThrow(() -> new EntityNotFoundException("Formation non trouvée avec l'ID : " + groupe.getFormation().getId()));   
        Formateur formateur = formateurRepository.findById(groupe.getFormateur().getId())
	                .orElseThrow(() -> new EntityNotFoundException("Formateur non trouvée avec l'ID : " + groupe.getFormateur().getId()));      
        Groupe old_groupe = createOrUpdateGroupe(groupe, formation, formateur);

        // Associez l'individu au groupe
        List<Individu> individus = individuRepository.findAllById(individuIds);   
        old_groupe.setIndividus(individus);

        // Sauvegardez le groupe
        groupeRepository.save(old_groupe);

    }

    private Groupe createOrUpdateGroupe(Groupe groupe, Formation formation, Formateur formateur) {
        // Recherchez un groupe existant pour la formation
        Optional<Groupe> groupeOptional = groupeRepository.findByFormation(formation);

        return groupeOptional.orElseGet(() -> {
            // Créez un nouveau groupe si aucun n'existe pour la formation
            Groupe nouveauGroupe = new Groupe();
            nouveauGroupe.setFormation(formation);
            nouveauGroupe.setFormateur(formateur);
            // Définissez d'autres attributs du groupe au besoin
            nouveauGroupe.setNom(groupe.getNom());
            return groupeRepository.save(nouveauGroupe);
        });
    }


    // Ajoutez d'autres méthodes au besoin

}

