package me.upskill.schoolattendance.controller;

import me.upskill.schoolattendance.exceptions.StatusCodeMyException;
import me.upskill.schoolattendance.util.ValidationUtil;

import static me.upskill.schoolattendance.api.ErrorCodes.MANDATORY_PARAMETER_MISSING;

// common root class for all controllers
public abstract class AbstractController {


    public static final String EMAIL_REGEX = "(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))";


    public void assertValid(String arg, String message) {
        try {
            ValidationUtil.assertValid(arg, message);
        } catch (IllegalArgumentException ex) {
            throw new StatusCodeMyException(MANDATORY_PARAMETER_MISSING, message, 400);
        }
    }

    public void assertNotNull(Object arg, String message) {
        try {
            if (arg == null) {
                throw new IllegalArgumentException(message);
            }
        } catch (IllegalArgumentException ex) {
            throw new StatusCodeMyException(MANDATORY_PARAMETER_MISSING, message, 400);
        }
    }

    public void assertValidEmail(String arg, String message) {
        try {
            ValidationUtil.assertValid(arg, message);
            if (!arg.matches(EMAIL_REGEX)) {
                throw new IllegalArgumentException("email is invalid");
            }
        } catch (IllegalArgumentException ex) {
            throw new StatusCodeMyException(MANDATORY_PARAMETER_MISSING, ex.getMessage(), 400);
        }
    }

}
