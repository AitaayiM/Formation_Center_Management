package com.gestion.formation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.formation.entity.Individu;
import com.gestion.formation.service.IndividuService;
import com.gestion.formation.util.Validator;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/inscriptions")
public class IndividuController {

    @Autowired
	private IndividuService individuService;

    @PostMapping("/individu")
    public ResponseEntity<?> inscrireIndividu(@RequestParam Long formationId, @RequestBody @Valid Individu individu, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                // Si des erreurs de validation sont présentes, renvoyer la liste des erreurs au frontend
                return new ResponseEntity<>(Validator.getValidationErrors(bindingResult), HttpStatus.BAD_REQUEST);
            }
    
            individuService.inscrireIndividu(formationId, individu);
            return new ResponseEntity<>("Individual successfully added", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allindividu")
    public ResponseEntity<List<Individu>> getAllIndividus() {
        List<Individu> individus = individuService.getListIndividus();
        return new ResponseEntity<>(individus, HttpStatus.OK);
    }
    

}
