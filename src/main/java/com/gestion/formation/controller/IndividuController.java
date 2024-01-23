package com.gestion.formation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.formation.dto.IndividuDTO;
import com.gestion.formation.entity.Individu;
import com.gestion.formation.service.IndividuService;
@RestController
@RequestMapping("/admin/inscriptions")
public class IndividuController {
	private final IndividuService individuService;

    @Autowired
    public IndividuController(IndividuService individuService) {
        this.individuService = individuService;
    }

    @PostMapping("/ajouter/{formationId}")
    public ResponseEntity<Individu> inscrireIndividu(@PathVariable Long formationId, @RequestBody @Valid IndividuDTO individuDTO) {
        Individu nouvelIndividu = individuService.inscrireIndividu(formationId, individuDTO);
        return new ResponseEntity<>(nouvelIndividu, HttpStatus.CREATED);
    }

    @PostMapping("/{individuId}/affecter-formateur/{formateurId}")
    public ResponseEntity<Void> affecterFormateur(@PathVariable Long individuId, @PathVariable Long formateurId) {
    	individuService.affecterFormateur(individuId, formateurId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
