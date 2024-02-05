package com.gestion.formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.formation.entity.Competence;

public interface CompetenceRepository extends JpaRepository<Competence, Long> {
    
}

