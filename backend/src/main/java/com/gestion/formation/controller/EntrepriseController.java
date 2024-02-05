package com.gestion.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gestion.formation.entity.Entreprise;
import com.gestion.formation.service.EntrepriseService;

import java.util.List;

@RestController
@RequestMapping("/admin/entreprises")
public class EntrepriseController {

    @Autowired
    private EntrepriseService entrepriseService;

    @PostMapping
    public ResponseEntity<String> ajouterEntreprise(@RequestBody Entreprise entreprise) {
        try {
            entrepriseService.ajouterEntreprise(entreprise);
            return new ResponseEntity<>("Entreprise ajouté avec succès", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Entreprise>> getListeEntreprises() {
        List<Entreprise> entreprises = entrepriseService.getListeEntreprises();
        return new ResponseEntity<>(entreprises, HttpStatus.OK);
    }

}

