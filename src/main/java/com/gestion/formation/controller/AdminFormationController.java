package com.gestion.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gestion.formation.dto.FormationDTO;
import com.gestion.formation.service.FormationService;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/formations")
@Secured("ADMIN")
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
}

