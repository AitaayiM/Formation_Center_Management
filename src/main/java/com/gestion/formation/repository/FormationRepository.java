package com.gestion.formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.formation.entity.Formation;

public interface FormationRepository extends JpaRepository <Formation, Long> {

}
