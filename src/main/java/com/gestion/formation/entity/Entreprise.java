package com.gestion.formation.entity;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Entreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le champ 'nom' de l'entreprise est requis")
    private String nom;

    @NotBlank(message = "Le champ 'adresse' de l'entreprise est requis")
    private String adresse;

    @Pattern(regexp = "^[0-9]*$", message = "Le champ 'telephone' doit contenir uniquement des chiffres")
    private String telephone;

    @Pattern(regexp = "^https?://\\S+$", message = "Le champ 'url' doit être une URL valide")
    private String url;

    @NotBlank(message = "Le champ 'email' de l'entreprise est requis")
    @Email(message = "Veuillez saisir une adresse email valide pour l'entreprise")
    private String email;

    @OneToMany(mappedBy = "entreprise")
    private List<Planification> planifications;
    
}
