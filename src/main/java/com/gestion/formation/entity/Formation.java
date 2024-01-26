package com.gestion.formation.entity;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @NotBlank(message = "Le champ 'categorie' de la formation est requis")
    private String categorie;

    @NotBlank(message = "Le champ 'ville' de la formation est requis")
    private String ville;

    @JsonIgnore
    @OneToMany(mappedBy = "formation")
    private List<Objectif> objectifs;

    @JsonIgnore
    @OneToMany(mappedBy = "formation")
    private List<Section> sections;

    @JsonIgnore
    @OneToMany(mappedBy = "formation")
    private List<Evaluation> evaluations;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "formation_individu",
        joinColumns = @JoinColumn(name = "formation_id"),
        inverseJoinColumns = @JoinColumn(name = "individu_id")
    )
    private List<Individu> individus;

    @OneToMany(mappedBy = "formation")
    private List<Groupe> groupes;
}
