package me.upskill.schoolattendance.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationEnvironmentPreparedEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationEnvironmentPreparedEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        LOGGER.info("Application environment prepared event received");
    }
}
