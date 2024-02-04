package com.gestion.formation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.formation.entity.Formation;
@Repository
public interface FormationRepository extends JpaRepository <Formation, Long> {

    List<Formation> findByCategorie(String categorie);

    List<Formation> findByVille(String ville);

}
