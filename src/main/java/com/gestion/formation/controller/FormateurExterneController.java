package com.gestion.formation.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.formation.dto.FormateurDTO;
import com.gestion.formation.service.FormateurService;

@RestController
@RequestMapping("/admin/formateurs")
//@Secured("ADMIN")
public class FormateurExterneController {

    private final FormateurService formateurService;

    public FormateurExterneController(FormateurService formateurService) {
        this.formateurService = formateurService;
    }

    @PostMapping
    public ResponseEntity<String> addFormateur(@Valid @RequestBody FormateurDTO formateurDTO) {
        try {
            formateurService.addFormateur(formateurDTO);
            return new ResponseEntity<>("Formateur ajouté avec succès", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

