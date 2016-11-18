/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pollub.cs.pentalearn.service.listeners;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import pl.pollub.cs.pentalearn.service.impl.LoginAttemptServiceImpl;

import javax.inject.Inject;


@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    private final LoginAttemptServiceImpl loginAttemptService;

    @Inject
    AuthenticationFailureListener(final LoginAttemptServiceImpl loginAttemptService){
        this.loginAttemptService=loginAttemptService;
    }
 
    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
        WebAuthenticationDetails auth = (WebAuthenticationDetails)
          e.getAuthentication().getDetails();
         
        loginAttemptService.loginFailed(auth.getRemoteAddress());
    }
}