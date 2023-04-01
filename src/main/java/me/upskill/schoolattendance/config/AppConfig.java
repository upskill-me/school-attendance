package me.upskill.schoolattendance.config;

import me.upskill.schoolattendance.controller.AuthenticationInterceptor;
import me.upskill.schoolattendance.properties.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "me.upskill.schoolattendance")
@EnableAutoConfiguration
@EnableConfigurationProperties(AppProperties.class)
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    private ApplicationContext context;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor bean = context.getBean(AuthenticationInterceptor.class);
        registry.addInterceptor(bean);
    }
}
