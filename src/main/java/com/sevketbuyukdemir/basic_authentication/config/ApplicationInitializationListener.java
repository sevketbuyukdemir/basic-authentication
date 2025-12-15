package com.sevketbuyukdemir.basic_authentication.config;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class ApplicationInitializationListener implements ApplicationListener<ContextRefreshedEvent> {
    private final Logger logger = LogManager.getLogger(ApplicationInitializationListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("Application is started...");
    }

}
