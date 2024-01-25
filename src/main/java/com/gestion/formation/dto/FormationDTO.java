package com.gestion.formation.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.gestion.formation.entity.Section;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FormationDTO {

    @NotBlank(message = "Le titre de la formation ne peut pas être vide")
    private String titre;

    @Positive(message = "Le nombre d'heures doit être un nombre positif")
    private int nombreHeures;

    @Positive(message = "Le coût doit être un nombre positif")
    private double cout;

    @NotBlank(message = "La description ne peut pas être vide")
    private String description;

    @NotBlank(message = "Le champ 'categorie' de la formation est requis")
    private String categorie;

    @NotBlank(message = "Le champ 'ville' de la formation est requis")
    private String ville;

    @NotNull(message = "La liste des objectifs ne peut pas être nulle")
    @Size(min = 1, message = "La formation doit avoir au moins un objectif")
    private List<String> objectifs;

    @NotNull(message = "La liste des sections ne peut pas être nulle")
    @Size(min = 1, message = "La formation doit avoir au moins un section")
    private List<Section> sections;
}

