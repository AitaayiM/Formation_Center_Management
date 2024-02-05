package com.gestion.formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.formation.entity.Individu;

public interface IndividuRepository extends JpaRepository<Individu, Long>     {
	Individu  findById(long id);
	boolean existsByEmail(String email);
	
	
}
