package com.gestion.formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.formation.entity.Objectif;

public interface ObjectifRepository extends JpaRepository<Objectif, Long> {
    
}

