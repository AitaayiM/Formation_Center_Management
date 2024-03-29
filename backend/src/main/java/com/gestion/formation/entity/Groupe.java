package com.gestion.formation.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Groupe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le champ 'nom' de la groupe est requis")
    private String nom;

    @ManyToOne
    @JoinColumn(name = "formation_id")
    @JsonIgnore  // Assurez-vous que le nom correspond à celui dans la table Groupe
    private Formation formation;

    @ManyToOne
    @JoinColumn(name = "formateur_id")
    @JsonIgnore
    private Formateur formateur;

    @ManyToMany
    @JoinTable(
        name = "groupe_individu",
        joinColumns = @JoinColumn(name = "groupe_id"),
        inverseJoinColumns = @JoinColumn(name = "individu_id"))
    @JsonIgnore
    private List<Individu> individus;

}


