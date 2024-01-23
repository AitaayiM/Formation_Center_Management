package com.gestion.formation.dto;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IndividuDTO {

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

    @Pattern(regexp = "\\d{10}", message = "Le numéro de téléphone doit contenir 10 chiffres")
    private String telephone;

    
}
