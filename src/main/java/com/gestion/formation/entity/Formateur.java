package com.gestion.formation.entity;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
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

    @NotEmpty(message = "La liste des compétences ne peut pas être vide")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "formateur_competences",
        joinColumns = @JoinColumn(name = "formateur_id"),
        inverseJoinColumns = @JoinColumn(name = "competence_id")
    )
    private Set<Competence> competences;

    @NotEmpty(message = "La liste des planifications ne peut pas être vide")
    @OneToMany(mappedBy = "formateur")
    private List<Planification> planifications;

}