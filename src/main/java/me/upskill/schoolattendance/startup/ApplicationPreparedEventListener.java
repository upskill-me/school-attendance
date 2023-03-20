package me.upskill.schoolattendance.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationPreparedEventListener implements ApplicationListener<ApplicationPreparedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationPreparedEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        LOGGER.info("Application prepared event received");
    }
}
