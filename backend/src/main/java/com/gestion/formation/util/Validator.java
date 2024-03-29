package com.gestion.formation.util;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class Validator {
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String TELEPONE_PATTERN = "^[0-9]*$";

    public static boolean isEmpty(String... values) {
        for (String value : values) {
            if(value == null || value.trim().isEmpty())
            return true;
        }
        return false;
    }

    public static boolean isDateInThePast(Date date) {
        if (date == null) {
            return false;
        }
        LocalDate localDate = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        return localDate.isBefore(LocalDate.now());
    }

    public static boolean isDateInTheFuture(Date... dates){
        for (Date date : dates) {
            LocalDate localDate = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            if(localDate.isBefore(LocalDate.now())){
                return false;
            }
        }
        return true;
    }

    public static boolean isGreaterThan(Date debut, Date fin){
        return fin.after(debut);
    }


    public static Map<String, String> getValidationErrors(BindingResult bindingResult) {
        // Créer une carte pour stocker les erreurs de validation
        Map<String, String> validationErrors = new HashMap<>();

        // Parcourir les erreurs de validation et les ajouter à la carte
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return validationErrors;
    }
}
