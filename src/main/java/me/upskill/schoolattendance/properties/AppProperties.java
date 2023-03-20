package me.upskill.schoolattendance.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("me.upskill.schoolattendance")
public class AppProperties {

    private String serviceName;

    private String serviceVersion;

    private String genericErrorMessage;

    private String secretKey;
}
