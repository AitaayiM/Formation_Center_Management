package com.gestion.formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.formation.entity.Formateur;
import java.util.Optional;

public interface FormateurRepository extends JpaRepository<Formateur, Long> {

    Optional<Formateur> findByUsername(String username);
    Optional<Formateur> findByEmail(String email);
}
