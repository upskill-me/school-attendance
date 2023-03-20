package me.upskill.schoolattendance;

import me.upskill.schoolattendance.config.AppConfig;
import me.upskill.schoolattendance.startup.ApplicationContextInitializedEventListener;
import me.upskill.schoolattendance.startup.ApplicationEnvironmentPreparedEventListener;
import me.upskill.schoolattendance.startup.ApplicationFailedEventListener;
import me.upskill.schoolattendance.startup.ApplicationPreparedEventListener;
import me.upskill.schoolattendance.startup.ApplicationReadyEventListener;
import me.upskill.schoolattendance.startup.ApplicationStartedEventListener;
import me.upskill.schoolattendance.startup.ApplicationStartingEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

public class Application {

    /**
     * application ready event listener
     * This listener is used to schedule cron jobs
     */
    private static final ApplicationListener<ApplicationReadyEvent> rlistener = new ApplicationReadyEventListener();

    private static final ApplicationListener<ApplicationContextInitializedEvent> clistener = new ApplicationContextInitializedEventListener();

    private static final ApplicationListener<ApplicationEnvironmentPreparedEvent> plistener = new ApplicationEnvironmentPreparedEventListener();

    private static final ApplicationListener<ApplicationFailedEvent> flistener = new ApplicationFailedEventListener();

    private static final ApplicationListener<ApplicationStartedEvent> stlistener = new ApplicationStartedEventListener();

    private static final ApplicationListener<ApplicationStartingEvent> slistener = new ApplicationStartingEventListener();

    private static final ApplicationListener<ApplicationPreparedEvent> pplistener = new ApplicationPreparedEventListener();

    /**
     * Application main entry point
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SpringApplication application = new SpringApplicationBuilder()
                .sources(AppConfig.class)
                .listeners(rlistener, clistener, plistener, flistener, stlistener, slistener, pplistener)
                .build();

        application.run(args);
    }
}
