package com.gestion.formation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.gestion.formation.entity.Evaluation;
import com.gestion.formation.entity.Formation;
import com.gestion.formation.repository.EvaluationRepository;
import com.gestion.formation.repository.FormationRepository;
import com.gestion.formation.util.TokenUtil;

@Service
public class EvaluationService {
 
    @Autowired
    private EvaluationRepository evaluationRepository;

     @Autowired
    private FormationRepository formationRepository;
 
    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.base-url}")
    private String baseUrl;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    public void sendEvaluationEmail(String email, Long formationId) {
        String token = TokenUtil.generateToken(email);
        String subject = "Formulaire d'évaluation de la formation";
        String body = "Bonjour,\n\nVous avez participé à notre formation. Veuillez cliquer sur le lien ci-dessous pour remplir le formulaire d'évaluation :\n\n"
                + baseUrl + "?formationId="+formationId+"&token="+token+"\n\nCordialement,\nVotre équipe de formation";
        sendEmail(email, subject, body);
    }

    public String generateEvaluationToken(String email) {
        return TokenUtil.generateToken(email);
    }

    public boolean validateEvaluationToken(String token, String email) {
        return TokenUtil.validateToken(token, email);
    }
 
    public Evaluation createEvaluation(Evaluation evaluation, Long formationId) {
        Formation formation = formationRepository.findById(formationId)
                .orElseThrow(() -> new IllegalArgumentException("Formation not found with id: " + formationId));

        evaluation.setFormation(formation);
        return evaluationRepository.save(evaluation);
    }
    
}
