package com.gestion.formation.dto;

import com.gestion.formation.entity.Formateur;
import com.gestion.formation.entity.Formation;
import com.gestion.formation.entity.Individu;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class GroupDTO {

    @NotBlank(message = "Le champ 'nom' de la groupe est requis")
    private String nom;

    private Formation formation;

    private Formateur formateur;

    private List<Individu> individus;

}


