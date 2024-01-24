package com.gestion.formation.util;

import java.time.LocalDate;
import java.util.Date;

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
}
