package me.upskill.schoolattendance.api;

public final class ErrorCodes {

    private ErrorCodes() {
    }

    public static final String INTERNAL_SERVER_ERROR = "E1-2500";

    public static final String MANDATORY_PARAMETER_MISSING = "E1-6000";

    public static final String INVALID_AUTH_HEADER = "E1-1000";
}
