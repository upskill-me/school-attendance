package me.upskill.schoolattendance.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationStartingEventListener implements ApplicationListener<ApplicationStartingEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationStartingEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        LOGGER.info("Application starting event received");
    }
}
