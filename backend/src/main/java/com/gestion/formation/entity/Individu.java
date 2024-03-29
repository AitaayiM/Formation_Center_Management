package com.gestion.formation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import com.gestion.formation.util.Validator;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Individu {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le champ 'nom' de l'individu est requis")
    private String nom;

    @NotBlank(message = "Le champ 'prenom' de l'individu est requis")
    private String prenom;

    @Past(message = "La date de naissance doit être dans le passé")
    private Date dateNaissance;

    @NotBlank(message = "Le champ 'ville' de l'individu est requis")
    private String ville;

    @NotBlank(message = "Le champ 'email' de l'individu est requis")
    @Email(message = "Veuillez saisir une adresse email valide")
    private String email;

    @Pattern(regexp = ""+Validator.TELEPONE_PATTERN+"", message = "Le numéro de téléphone doit contenir 10 chiffres")
    private String telephone;

    @ManyToMany
    private List<Formation> formations;

    @ManyToMany(mappedBy = "individus")
    private List<Groupe> groupes;

}
