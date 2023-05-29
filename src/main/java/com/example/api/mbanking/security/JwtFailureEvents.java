package com.example.api.mbanking.security;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;

public class JwtFailureEvents {
    @EventListener
    public void onFailure(AuthenticationFailureBadCredentialsEvent credentialsEvent){
        if(credentialsEvent.getAuthentication() instanceof BearerTokenAuthenticationToken){
            System.out.println(credentialsEvent.getAuthentication().getCredentials());
        }
    }
}
