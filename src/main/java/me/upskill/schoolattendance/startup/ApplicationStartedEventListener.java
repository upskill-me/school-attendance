package me.upskill.schoolattendance.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationStartedEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        LOGGER.info("Application started event received");
    }
}
