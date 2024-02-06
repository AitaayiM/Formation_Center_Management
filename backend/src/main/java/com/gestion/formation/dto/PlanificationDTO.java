package com.gestion.formation.dto;

import java.util.Date;

import com.gestion.formation.entity.Entreprise;
import com.gestion.formation.entity.Formateur;
import com.gestion.formation.entity.Formation;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PlanificationDTO {

    @NotEmpty(message = "La formation ne peut pas être vide")
    private Formation formation;

    @NotEmpty(message = "Le formateur ne peut pas être vide")
    private Formateur formateur;

    @NotEmpty(message = "L'etreprise ne peut pas être vide")
    private Entreprise entreprise;

    @Temporal(TemporalType.TIMESTAMP)
    @FutureOrPresent(message = "La date de début doit être dans le futur ou le présent")
    private Date dateDebut;

    @Temporal(TemporalType.TIMESTAMP)
    @FutureOrPresent(message = "La date de fin doit être dans le futur ou le présent")
    private Date dateFin;
}

