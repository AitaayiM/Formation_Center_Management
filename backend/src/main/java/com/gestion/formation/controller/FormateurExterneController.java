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

import com.gestion.formation.dto.FormateurExterneSignUpDTO;
import com.gestion.formation.entity.Formateur;
import com.gestion.formation.entity.Formation;
import com.gestion.formation.service.FormateurExterneService;
import com.gestion.formation.service.FormationService;
import com.gestion.formation.util.Validator;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/formateurs")
public class FormateurExterneController {

    @Autowired
    private FormateurExterneService formateurService;

    @Autowired
    private FormationService formationService;

    @Autowired
    private AuthController authController;

    @PostMapping("/formations-interessees")
    public ResponseEntity<?> updateFormationsInteressees(@Valid @RequestBody FormateurExterneSignUpDTO formateurExterne, @RequestParam Long formationId, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return new ResponseEntity<>(Validator.getValidationErrors(bindingResult), HttpStatus.BAD_REQUEST);
            }

            // Authentifier le formateur en utilisant les informations d'identification fournies dans le corps de la requête
            ResponseEntity<?> authenticationResponse = authController.authenticateUser(formateurExterne, bindingResult);
            if (authenticationResponse.getStatusCode() != HttpStatus.OK) {
                return authenticationResponse; // Renvoie une réponse d'erreur si l'authentification échoue
            }

            // L'authentification a réussi, continuez le processus pour permettre au formateur de s'inscrire à la formation
            Formateur formateur = formateurService.findByUsernameOrEmail(formateurExterne.getUsernameOrEmail());
            boolean isFound= false;
            for (Formation formations : formateur.getFormationsInteressees()) {
                if (formationId == formations.getId()) {
                    isFound = true;
                }
            }

            if (!isFound) {
                formateur.setExterne(true);
                formateur.getCompetences().addAll(formateurExterne.getCompetences());
            }

            Formation formationsInteresse = formationService.findById(formationId);
            formateur.getFormationsInteressees().add(formationsInteresse);
            formateurService.addFormateur(formateur);

            return new ResponseEntity<>("Formateur ajouté avec succès", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Formateur>> getFormateurs() {
        List<Formateur> formateurs = formateurService.getAllFormateurs();
        if (formateurs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(formateurs);
    }
    

}

