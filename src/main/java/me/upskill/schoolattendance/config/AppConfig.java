package me.upskill.schoolattendance.config;

import me.upskill.schoolattendance.properties.AppProperties;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "me.upskill.schoolattendance")
@EnableAutoConfiguration
@EnableConfigurationProperties(AppProperties.class)
public class AppConfig {
}
