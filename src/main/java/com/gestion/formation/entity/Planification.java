package com.gestion.formation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.Date;

import javax.validation.constraints.FutureOrPresent;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Planification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "formation_id")
    private Formation formation;

    @ManyToOne
    @JoinColumn(name = "formateur_id")
    private Formateur formateur;

    @ManyToOne
    @JoinColumn(name = "entreprise_id")
    private Entreprise entreprise;

    @Temporal(TemporalType.TIMESTAMP)
    @FutureOrPresent(message = "La date de début doit être dans le futur ou le présent")
    private Date dateDebut;

    @Temporal(TemporalType.TIMESTAMP)
    @FutureOrPresent(message = "La date de fin doit être dans le futur ou le présent")
    private Date dateFin;

}

