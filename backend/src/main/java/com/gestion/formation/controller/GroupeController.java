package com.gestion.formation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.formation.dto.GroupDTO;
import com.gestion.formation.service.GroupeService;
import com.gestion.formation.util.Validator;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/groupe")
public class GroupeController {
    
    @Autowired
    private GroupeService groupeService;

    @PostMapping("/affecter-formateur")
    public ResponseEntity<?> affecterFormateurAuGroupe(@RequestBody GroupDTO groupe, @RequestParam List<Long> individuIds) {
        try {
            groupeService.inscrireIndividuAFormation(groupe, individuIds);
            return new ResponseEntity<>("Formateur affecté au groupe avec succès", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

