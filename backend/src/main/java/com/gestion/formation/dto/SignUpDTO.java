package com.gestion.formation.dto;

import java.util.Set;

import com.gestion.formation.entity.Competence;
import com.gestion.formation.entity.Remarque;
import com.gestion.formation.util.FormateurValidationGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class SignUpDTO {

    @NotBlank(message = "Le nom ne peut pas être vide.")
    private String name;

    @NotBlank(message = "Le nom d'utilisateur ne peut pas être vide.")
    private String username;

    @NotBlank(message = "L'e-mail ne peut pas être vide.")
    @Email(message = "L'e-mail doit être valide.")
    private String email;

    @NotBlank(message = "Le mot de passe ne peut pas être vide.")
    @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères.")
    private String password;

    @NotEmpty(groups = FormateurValidationGroup.class, message = "La liste des compétences ne peut pas être vide")
    private Set<Competence> competences;

    @NotEmpty(groups = FormateurValidationGroup.class, message = "La liste des remarques ne peut pas être vide")
    private Set<Remarque> remarques;
}
