package com.gestion.formation.entity;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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

    @NotBlank(message = "La description ne peut pas être vide")
    private String description;

    @Positive(message = "Le nombre d'heures doit être un nombre positif")
    private int nombreHeures;

    @Positive(message = "Le coût doit être un nombre positif")
    private double cout;

    @NotBlank(message = "Le champ 'categorie' de la formation est requis")
    private String categorie;

    @NotBlank(message = "Le champ 'ville' de la formation est requis")
    private String ville;

    @NotEmpty(message = "La liste des objectifs ne peut pas être vide")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "formation_objectifs",
        joinColumns = @JoinColumn(name = "formation_id"),
        inverseJoinColumns = @JoinColumn(name = "objectif_id")
    )
    private List<Objectif> objectifs;

    @NotEmpty(message = "La liste des sections ne peut pas être vide")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "formation_sections",
        joinColumns = @JoinColumn(name = "formation_id"),
        inverseJoinColumns = @JoinColumn(name = "section_id")
    )
    private List<Section> sections;

    
    @OneToMany(mappedBy = "formation")
    private List<Planification> planifications; // Ajouter une liste de planifications

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
