package com.xpeppers.linkingcommerce.merchantdashboard.models;

import java.util.HashMap;
import java.util.Map;

public class SignInValidator implements Validator {

    private static final int MINIMUM_PASSWORD_LENGTH = 1;

    @Override
    public Map<String, String> validate(String email, String password) {
        Map<String, String> validationErrors = new HashMap<>();

        if (password.length() < MINIMUM_PASSWORD_LENGTH) {
            validationErrors.put("password", "too short");
        }

        if (!email.contains("@")) {
            validationErrors.put("email", "invalid format");
        }
        return validationErrors;
    }
}
