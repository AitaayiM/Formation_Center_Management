package com.gestion.formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.formation.entity.Evaluation;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    
}

