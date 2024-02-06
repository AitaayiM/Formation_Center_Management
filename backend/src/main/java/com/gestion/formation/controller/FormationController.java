package com.gestion.formation.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gestion.formation.dto.FormationDTO;
import com.gestion.formation.entity.Formation;
import com.gestion.formation.service.FormationService;
import com.gestion.formation.util.Validator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/formations")
@Validated
public class FormationController {

    @Autowired
    private FormationService formationService;

    @PostMapping("/admin")
    public ResponseEntity<?> addFormation(@Valid @RequestBody FormationDTO formationDTO, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return new ResponseEntity<>(Validator.getValidationErrors(bindingResult), HttpStatus.BAD_REQUEST);
            }
            formationService.createFormation(formationDTO);
            return new ResponseEntity<>("Formation ajoutée avec succès", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/filtres")
    public List<Formation> getFormationsWithFilters(
            @RequestParam(required = false) String categorie,
            @RequestParam(required = false) String ville,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

        return formationService.getFormationsWithFilters(categorie, ville, date);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Formation>> getAllFormations(){
        List<Formation> formations = formationService.getAllFormations();
        if (formations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(formations);
    }
}

