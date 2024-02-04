package com.gestion.formation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.formation.entity.Formateur;
import com.gestion.formation.repository.FormateurRepository;

import java.util.Optional;

@Service
public class FormateurExterneService {

    @Autowired
    private FormateurRepository formateurRepository;

    public void addFormatdeur(Formateur formateur) {
        Optional<Formateur> isNull = Optional.ofNullable(formateur);  
        if(isNull.isPresent()){
            formateurRepository.save(isNull.get());
        };
    }

    public void addFormateur(Formateur formateur) {
        Optional<Formateur> isNull = Optional.ofNullable(formateur);  
        if(isNull.isPresent()) formateurRepository.save(formateur);
    }

    public Formateur findByUsernameOrEmail(String UsernameOrEmail) {
        Formateur formateurEmail;
        Optional<Formateur> formateurUsername = formateurRepository.findByUsername(UsernameOrEmail);
        if (!formateurUsername.isPresent()) {
            formateurEmail = formateurRepository.findByEmail(UsernameOrEmail)
            .orElseThrow(() -> new RuntimeException("Formateur non trouvée"));
        }else {
            return formateurUsername.get();
        }
        return formateurEmail;
    }
}

