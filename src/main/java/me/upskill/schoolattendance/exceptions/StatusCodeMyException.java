package me.upskill.schoolattendance.exceptions;

import lombok.Getter;

// this will  result in client defined status code
@Getter
public class StatusCodeMyException extends MyException {

    // http status code
    private final int statusCode;

    public StatusCodeMyException(String code, String message, int statusCode) {
        super(code, message);
        this.statusCode = statusCode;
    }
}
