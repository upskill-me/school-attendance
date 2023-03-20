package me.upskill.schoolattendance.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class ApiError {

    private String serviceName;

    private String serviceVersion;

    private String code;

    private String message;

    private Map<String, Object> info;
}
