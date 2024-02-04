package com.gestion.formation.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.formation.entity.Individu;
import com.gestion.formation.service.EvaluationService;
import com.gestion.formation.service.IndividuService;
import com.gestion.formation.util.Validator;

@RestController
@RequestMapping("/inscriptions")
public class IndividuController {

    @Autowired
	private IndividuService individuService;

    @Autowired
    private EvaluationService evaluationService;

    @PostMapping("/send")
    public ResponseEntity<String> inscrireIndividus(@RequestParam String individuEmail, @RequestParam Long formationId) {
        try{
            evaluationService.sendEvaluationFormLink(individuEmail, formationId);
            return new ResponseEntity<>("message sending avec succès", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEvaluationEmail(@RequestParam String email, @RequestParam String token) {
        try {
            evaluationService.sendEvaluationEmail(email, token);
            return ResponseEntity.ok("L'e-mail d'évaluation a été envoyé avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de l'envoi de l'e-mail d'évaluation.");
        }
    }

    @PostMapping("/individu")
    public ResponseEntity<?> inscrireIndividu(@RequestParam Long formationId, @RequestBody @Valid Individu individu, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                // Si des erreurs de validation sont présentes, renvoyer la liste des erreurs au frontend
                return new ResponseEntity<>(Validator.getValidationErrors(bindingResult), HttpStatus.BAD_REQUEST);
            }
    
            individuService.inscrireIndividu(formationId, individu);
            return new ResponseEntity<>("Individu ajouté avec succès", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
