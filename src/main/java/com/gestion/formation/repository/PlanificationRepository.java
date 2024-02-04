package com.gestion.formation.repository;


import java.util.Date;
import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.formation.entity.Formation;
import com.gestion.formation.entity.Planification;

public interface PlanificationRepository extends JpaRepository<Planification, Long> {

    Optional<List<Planification>> findByDateDebut(Date date);

    List<Planification> findByFormation(Formation formation);
    
}

