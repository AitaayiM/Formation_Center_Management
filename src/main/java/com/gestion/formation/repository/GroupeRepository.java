package com.gestion.formation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.formation.entity.Formation;
import com.gestion.formation.entity.Groupe;

public interface GroupeRepository extends JpaRepository<Groupe, Long> {

    Optional<Groupe> findByFormation(Formation formation);

}
