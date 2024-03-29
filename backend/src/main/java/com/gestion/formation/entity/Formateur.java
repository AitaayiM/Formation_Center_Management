package com.gestion.formation.entity;

import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.NotEmpty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("formateur")
public class Formateur extends User {

    private boolean isExterne = false;

    @NotEmpty(message = "La liste des compétences ne peut pas être vide")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "formateur_competences",
        joinColumns = @JoinColumn(name = "formateur_id"),
        inverseJoinColumns = @JoinColumn(name = "competence_id")
    )
    private Set<Competence> competences;

    @NotEmpty(message = "La liste des remarques ne peut pas être vide")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "formateur_remarques",
        joinColumns = @JoinColumn(name = "formateur_id"),
        inverseJoinColumns = @JoinColumn(name = "remarque_id")
    )
    private Set<Remarque> remarques;

    @OneToMany(mappedBy = "formateur")
    private List<Planification> planifications;

    @OneToMany(mappedBy = "formateur")
    private List<Groupe> groupes;

    @ManyToMany
    @JoinTable(
        name = "formateur_formation",
        joinColumns = @JoinColumn(name = "formateur_id"),
        inverseJoinColumns = @JoinColumn(name = "formation_id"))
    private List<Formation> formationsInteressees;
}