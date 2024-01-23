package com.gestion.formation.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.gestion.formation.entity.Competence;

public class FormateurDTO {

    @NotBlank(message = "Le champ 'name' est requis")
    private String name;

    @NotBlank(message = "Le champ 'username' est requis")
    private String username;

    @NotBlank(message = "Le champ 'email' est requis")
    @Email(message = "Veuillez saisir une adresse email valide")
    private String email;

    @NotBlank(message = "Le champ 'password' est requis")
    @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères")
    private String password;

    @NotEmpty(message = "La liste des compétences ne peut pas être vide")
    private Set<Competence> competences;

}
