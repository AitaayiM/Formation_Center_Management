package com.gestion.formation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.formation.entity.Individu;
import com.gestion.formation.service.IndividuService;

@RestController
@RequestMapping("/admin/inscriptions")
public class IndividuController {

    @Autowired
	private IndividuService individuService;

    @PostMapping
    public ResponseEntity<String> inscrireIndividu(@RequestParam Long formationId, @RequestBody @Valid Individu individu) {
        try{
            individuService.inscrireIndividu(formationId, individu);
        return new ResponseEntity<>("Individu ajouté avec succès", HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

}
