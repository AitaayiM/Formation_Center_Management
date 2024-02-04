package com.gestion.formation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.formation.dto.PlanificationDTO;
import com.gestion.formation.entity.Planification;
import com.gestion.formation.service.PlanificationService;

@RestController
@RequestMapping("/admin/planifications")
public class PlanificationController {

    @Autowired
    private PlanificationService planificationService;

    @PostMapping
    public ResponseEntity<String> planifierFormation(@RequestBody PlanificationDTO planification) {
        try {
            planificationService.planifierFormation(planification);
            return new ResponseEntity<>("Formation planifiée avec succès", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Planification>> getListePlanifications() {
        List<Planification> planifications = planificationService.getListePlanifications();
        return new ResponseEntity<>(planifications, HttpStatus.OK);
    }
}

