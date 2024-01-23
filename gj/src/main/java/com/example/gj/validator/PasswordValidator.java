package com.example.gj.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    public static boolean isValidPassword(String password) {
        String patternString = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

        Pattern pattern = Pattern.compile(patternString);

        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }

}
