package com.gestion.formation.service;

import org.springframework.stereotype.Service;

import com.gestion.formation.dto.FormateurDTO;
import com.gestion.formation.entity.Formateur;
import com.gestion.formation.mapper.FormateurMapper;
import com.gestion.formation.repository.FormateurRepository;

@Service
public class FormateurService {

    private final FormateurRepository formateurRepository;
    private final FormateurMapper formateurMapper;

    public FormateurService(FormateurRepository formateurRepository, FormateurMapper formateurMapper) {
        this.formateurRepository = formateurRepository;
        this.formateurMapper = formateurMapper;
    }

    public void addFormateur(FormateurDTO formateurDTO) {
        Formateur formateur = formateurMapper.convertToEntity(formateurDTO);
        formateurRepository.save(formateur);
    }
}

