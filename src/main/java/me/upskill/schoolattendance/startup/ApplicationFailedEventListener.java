package me.upskill.schoolattendance.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationFailedEventListener implements ApplicationListener<ApplicationFailedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationFailedEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
        LOGGER.info("Application failed event received");
    }
}
