package com.gestion.formation.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.formation.dto.IndividuDTO;
import com.gestion.formation.entity.Formateur;
import com.gestion.formation.entity.Formation;
import com.gestion.formation.entity.Individu;
import com.gestion.formation.mapper.IndividuMapper;
import com.gestion.formation.repository.FormateurRepository;
import com.gestion.formation.repository.FormationRepository;
import com.gestion.formation.repository.IndividuRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class IndividuService {
	 private final IndividuRepository individuRepository;
	    private final FormationRepository formationRepository;
	    private final FormateurRepository formateurRepository;
	    private final IndividuMapper individuMapper;

	    @Autowired
	    public IndividuService(IndividuRepository individuRepository, FormationRepository formationRepository, FormateurRepository formateurRepository, IndividuMapper individuMapper) {
	        this.individuRepository = individuRepository;
	        this.formationRepository = formationRepository;
	        this.formateurRepository = formateurRepository;
	        this.individuMapper = individuMapper;
	    }

	    public Individu inscrireIndividu(Long formationId, IndividuDTO individuDTO) {
	        Individu nouvelIndividu = individuMapper.convertToEntity(individuDTO);

	        Formation formation = formationRepository.findById(formationId)
	                .orElseThrow(() -> new EntityNotFoundException("Formation non trouvée avec l'ID : " + formationId));

	        nouvelIndividu.setFormations(Collections.singletonList(formation));

	        return individuRepository.save(nouvelIndividu);
	    }

	    
	
}
