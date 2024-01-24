package com.gestion.formation.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.formation.entity.Formation;
import com.gestion.formation.entity.Individu;
import com.gestion.formation.repository.FormationRepository;
import com.gestion.formation.repository.IndividuRepository;
import com.gestion.formation.util.Validator;

import jakarta.persistence.EntityNotFoundException;

@Service
public class IndividuService {

	@Autowired
	private IndividuRepository individuRepository;

	@Autowired
	private FormationRepository formationRepository;

	public void inscrireIndividu(Long formationId, Individu individu) {
		if (Validator.isEmpty(individu.getNom(), individu.getPrenom(), individu.getTelephone(), individu.getEmail(), individu.getVille())) {
			throw new IllegalArgumentException("Tous les champs doivent être renseignés");
		}else if (!individu.getTelephone().matches(Validator.TELEPONE_PATTERN)) {
			throw new IllegalArgumentException("Le champ 'telephone' doit contenir uniquement des chiffres");
		}else if (!individu.getEmail().matches(Validator.EMAIL_PATTERN)) {
			throw new IllegalArgumentException("Le champ 'email' doit être valide");
		}else if(!Validator.isDateInThePast(individu.getDateNaissance())){
			throw new IllegalArgumentException("La date de naissance doit être dans le passé");
		}
		Formation formation = formationRepository.findById(formationId)
	                .orElseThrow(() -> new EntityNotFoundException("Formation non trouvée avec l'ID : " + formationId));
	    individu.setFormations(Collections.singletonList(formation));
	    individuRepository.save(individu);
	}
	
}
