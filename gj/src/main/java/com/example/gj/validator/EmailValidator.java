package com.example.gj.validator;

import java.util.regex.Pattern;

public class EmailValidator {
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        return pattern.matcher(email).matches();
    }
}
