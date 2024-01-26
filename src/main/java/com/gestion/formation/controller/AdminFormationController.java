package com.gestion.formation.controller;

import java.util.Date;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/admin/formations")
//@Secured("ADMIN")
@Validated
public class AdminFormationController {

    @Autowired
    private FormationService formationService;

    @PostMapping
    public ResponseEntity<String> addFormation(@Valid @RequestBody FormationDTO formationDTO) {
        try {
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
}

