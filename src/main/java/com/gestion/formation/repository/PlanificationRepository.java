package com.gestion.formation.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.formation.entity.Planification;

public interface PlanificationRepository extends JpaRepository<Planification, Long> {
    
}

