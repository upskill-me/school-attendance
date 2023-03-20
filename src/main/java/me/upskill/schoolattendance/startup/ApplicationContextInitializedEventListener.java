package me.upskill.schoolattendance.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationContextInitializedEventListener implements ApplicationListener<ApplicationContextInitializedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContextInitializedEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationContextInitializedEvent event) {
        LOGGER.info("Application context initialized event received");
    }
}
