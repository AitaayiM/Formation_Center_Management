package com.gestion.formation.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginDTO {

    @NotBlank(message = "Le nom d'utilisateur ou l'e-mail ne peut pas être vide.")
    private String usernameOrEmail;
    
    @NotBlank(message = "Le mot de passe ne peut pas être vide.")
    private String password;
}
