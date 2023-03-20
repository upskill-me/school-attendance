package me.upskill.schoolattendance.exceptions;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

// this is the root exception class of every exception in my application
// this will always result in 500 status code
@Getter
public class MyException extends RuntimeException {

    private final String code;

    private final Map<String, Object> info;

    public MyException(String code, String message) {
        super(message);
        this.code = code;
        this.info = new LinkedHashMap<>();
    }

    public MyException addInfo(String key, Object value) {
        this.info.put(key, value);
        return this;
    }
}
