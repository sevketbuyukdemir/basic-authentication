package com.sevketbuyukdemir.basic_authentication.security.events;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEvents {
    private final Logger logger = LogManager.getLogger(AuthenticationEvents.class);

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent success) {
        String log = String.format("IsAuthenticated: %1s, Name: %2s", success.getAuthentication().isAuthenticated(), ((UsernamePasswordAuthenticationToken) success.getSource()).getName());
        logger.info(log);
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failures) {
        String log = String.format("IsAuthenticated: %1s, Name: %2s", failures.getAuthentication().isAuthenticated(), ((UsernamePasswordAuthenticationToken) failures.getSource()).getName());
        logger.info(log);
    }
}
