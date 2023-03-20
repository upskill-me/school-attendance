package me.upskill.schoolattendance.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        LOGGER.info("Application ready event received");
    }
}
