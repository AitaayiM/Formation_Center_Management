package com.gestion.formation.entity;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le champ 'titre' de la formation est requis")
    private String titre;

    @Positive(message = "Le nombre d'heures doit être un nombre positif")
    private int nombreHeures;

    @Positive(message = "Le coût doit être un nombre positif")
    private double cout;

    @OneToMany(mappedBy = "formation")
    private List<Objectif> objectifs;

    @OneToMany(mappedBy = "formation")
    private List<Section> sections;

    @OneToMany(mappedBy = "formation")
    private List<Evaluation> evaluations;

    @ManyToMany
    @JoinTable(
        name = "formation_individu",
        joinColumns = @JoinColumn(name = "formation_id"),
        inverseJoinColumns = @JoinColumn(name = "individu_id")
    )
    private List<Individu> individus;
}
