package com.gestion.formation.service;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.gestion.formation.entity.Evaluation;
import com.gestion.formation.repository.EvaluationRepository;
import com.gestion.formation.util.TokenUtil;

@Service
public class EvaluationService {
 
    @Autowired
    private EvaluationRepository evaluationRepository;
 
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

    public void sendEvaluationFormLink(String participantEmail, Long formationId) {
        String evaluationFormLink = "https://votre-domaine.com/formulaire-evaluation?formationId=" + formationId;

        String subject = "Formulaire d'évaluation de la formation";
        String body = "Cher participant,\n\nMerci d'avoir participé à notre formation. Veuillez cliquer sur le lien ci-dessous pour accéder au formulaire d'évaluation :\n\n" + evaluationFormLink + "\n\nCordialement,\nVotre équipe de formation";

        sendEmail(participantEmail, subject, body);
    }

    public void sendEvaluationEmail(String to, String token) {
        String subject = "Formulaire d'évaluation de la formation";
        String body = "Bonjour,\n\nVous avez participé à notre formation. Veuillez cliquer sur le lien ci-dessous pour remplir le formulaire d'évaluation :\n\n"
                + baseUrl + "/evaluation?token=" + token + "\n\nCordialement,\nVotre équipe de formation";
        sendEmail(to, subject, body);
    }

    public String generateEvaluationToken(String email) {
        return TokenUtil.generateToken(email);
    }

    public boolean validateEvaluationToken(String token, String email) {
        return TokenUtil.validateToken(token, email);
    }
 
    public Evaluation createEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    public List<Evaluation> getEvaluationsByFormation(Long formationId) {
        return evaluationRepository.findByFormationId(formationId);
    }


    
}
