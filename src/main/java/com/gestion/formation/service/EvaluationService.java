package com.gestion.formation.service;

import com.gestion.formation.entity.Evaluation;
import com.gestion.formation.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;
/* 
    public Evaluation submitEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    public List<Evaluation> getEvaluationsByFormation(Long formationId) {
        return evaluationRepository.findByFormationId(formationId);
    }*/
}
