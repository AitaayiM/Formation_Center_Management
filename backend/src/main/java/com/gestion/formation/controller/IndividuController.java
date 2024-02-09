package com.gestion.formation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.formation.entity.Individu;
import com.gestion.formation.service.EvaluationService;
import com.gestion.formation.service.IndividuService;
import com.gestion.formation.util.Validator;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/inscriptions")
public class IndividuController {

    @Autowired
	private IndividuService individuService;

    @Autowired
    private EvaluationService evaluationService;

    @PostMapping("/admin/send")
    public ResponseEntity<String> inscrireIndividus(@RequestParam String individuEmail, @RequestParam Long formationId) {
        try{
            evaluationService.sendEvaluationFormLink(individuEmail, formationId);
            return new ResponseEntity<>("message sending successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/admin/sendemail")
    public ResponseEntity<String> sendEvaluationEmail(@RequestParam List<String> emails) {
        try {
            for (String email : emails) {
                evaluationService.sendEvaluationEmail(email);
            }
            return ResponseEntity.ok("The evaluation email has been sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while sending the review email.");
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

    @GetMapping("/allindividu")
    public ResponseEntity<List<Individu>> getAllIndividus() {
        List<Individu> individus = individuService.getListIndividus();
        return new ResponseEntity<>(individus, HttpStatus.OK);
    }
    

}
