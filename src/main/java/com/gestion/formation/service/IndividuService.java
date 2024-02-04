package com.gestion.formation.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.formation.entity.Formation;
import com.gestion.formation.entity.Individu;
import com.gestion.formation.repository.FormationRepository;
import com.gestion.formation.repository.IndividuRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class IndividuService {

	@Autowired
	private IndividuRepository individuRepository;

	@Autowired
	private FormationRepository formationRepository;

	public void inscrireIndividu(Long formationId, Individu individu) {
		Formation formation = formationRepository.findById(formationId)
	                .orElseThrow(() -> new EntityNotFoundException("Formation non trouvée avec l'ID : " + formationId));
	    individu.setFormations(Collections.singletonList(formation));
	    individuRepository.save(individu);
	}
	
}
