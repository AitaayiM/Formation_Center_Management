package com.gestion.formation.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Evaluation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "La note de qualité pédagogique doit être au moins 0")
    @Max(value = 5, message = "La note de qualité pédagogique ne peut pas dépasser 5")
    private int noteQualitePedagogique;

    @Min(value = 0, message = "La note de rythme doit être au moins 0")
    @Max(value = 5, message = "La note de rythme ne peut pas dépasser 5")
    private int noteRythme;

    @Min(value = 0, message = "La note de support de cours doit être au moins 0")
    @Max(value = 5, message = "La note de support de cours ne peut pas dépasser 5")
    private int noteSupportCours;

    @Min(value = 0, message = "La note de TP doit être au moins 0")
    @Max(value = 5, message = "La note de TP ne peut pas dépasser 5")
    private int noteTP;

    @Min(value = 0, message = "La note de maîtrise du sujet doit être au moins 0")
    @Max(value = 5, message = "La note de maîtrise du sujet ne peut pas dépasser 5")
    private int noteMaitriseSujet;

    @ManyToOne
    @JoinColumn(name = "formation_id")
    private Formation formation;
}
