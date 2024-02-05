package com.gestion.formation.dto;

import java.util.Set;

import com.gestion.formation.entity.Competence;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FormateurExterneSignUpDTO extends LoginDTO{
    
    private boolean isExterne = false;

    @NotEmpty(message = "La liste des compétences ne peut pas être vide")
    private Set<Competence> competences;

}
