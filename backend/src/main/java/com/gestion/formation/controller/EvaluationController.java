package com.gestion.formation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.formation.entity.Evaluation;
import com.gestion.formation.service.EvaluationService;
import com.gestion.formation.util.Validator;

import jakarta.validation.Valid;

@RestController
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;


    @PostMapping("/admin/sendemail")
    public ResponseEntity<String> sendEvaluationEmail(@RequestParam List<String> emails, @RequestParam Long formationId) {
        try {
            for (String email : emails) {
                evaluationService.sendEvaluationEmail(email, formationId);
            }
            return ResponseEntity.ok("The evaluation email has been sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while sending the review email.");
        }
    }

    @PostMapping("/evaluation/validate")
    public boolean validateEvaluationToken(@RequestParam String token, @RequestParam String email) {
        return evaluationService.validateEvaluationToken(token, email);
    }

    @PostMapping("/review")
    public ResponseEntity<?> createReview(@Valid @RequestBody Evaluation review, @RequestParam Long formationId, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                // Si des erreurs de validation sont présentes, renvoyer la liste des erreurs au frontend
                return new ResponseEntity<>(Validator.getValidationErrors(bindingResult), HttpStatus.BAD_REQUEST);
            }
            evaluationService.createEvaluation(review, formationId);
            return new ResponseEntity<>("review successfully added", HttpStatus.OK);
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating the review.");
        }
    }
}

