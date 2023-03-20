package me.upskill.schoolattendance.util;

import org.apache.commons.lang3.StringUtils;

public final class ValidationUtil {

    private ValidationUtil() {
    }

    public static void assertValid(String arg, String message) {
        if (StringUtils.isEmpty(arg)) {
            throw new IllegalArgumentException(message);
        }
    }
}
