package com.gestion.formation.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.formation.entity.Entreprise;

import com.gestion.formation.repository.EntrepriseRepository;
import com.gestion.formation.util.Validator;

import java.util.List;

@Service
public class EntrepriseService {

    @Autowired
    private EntrepriseRepository entrepriseRepository;
    
    public void ajouterEntreprise(Entreprise entreprise) {
        if(Validator.isEmpty(entreprise.getNom(), entreprise.getUrl(), entreprise.getEmail(), entreprise.getAdresse(), entreprise.getTelephone())){
        //if (isEmpty(entreprise.getNom()) || isEmpty(entreprise.getUrl()) || isEmpty(entreprise.getEmail()) || isEmpty(entreprise.getAdresse()) || isEmpty(entreprise.getTelephone())) {
            throw new IllegalArgumentException("Tous les champs doivent être renseignés");
        }else if (!entreprise.getTelephone().matches(Validator.TELEPONE_PATTERN)) {
            throw new IllegalArgumentException("Le champ 'telephone' doit contenir uniquement des chiffres");
        }else if (!entreprise.getEmail().matches(Validator.EMAIL_PATTERN)) {
            throw new IllegalArgumentException("Le champ 'email' doit être valide");
        }
        

        entrepriseRepository.save(entreprise);
    }

    public List<Entreprise> getListeEntreprises() {
        return entrepriseRepository.findAll();
    }
}
