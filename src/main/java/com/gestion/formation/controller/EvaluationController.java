package com.gestion.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.formation.service.EvaluationService;

@RestController
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @PostMapping("/evaluation/token")
    public String generateEvaluationToken(@RequestBody String email) {
        return evaluationService.generateEvaluationToken(email);
    }

    @PostMapping("/evaluation/validate")
    public boolean validateEvaluationToken(@RequestBody String token, @RequestBody String email) {
        return evaluationService.validateEvaluationToken(token, email);
    }
}

