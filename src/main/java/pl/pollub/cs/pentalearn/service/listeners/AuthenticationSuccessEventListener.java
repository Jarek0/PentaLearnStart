/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pollub.cs.pentalearn.service.listeners;



import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import pl.pollub.cs.pentalearn.domain.User;
import pl.pollub.cs.pentalearn.service.UserService;
import pl.pollub.cs.pentalearn.service.impl.LoginAttemptServiceImpl;

import javax.inject.Inject;


@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private final UserService userService;

    private final LoginAttemptServiceImpl loginAttemptService;

    @Inject
    AuthenticationSuccessEventListener (final LoginAttemptServiceImpl loginAttemptService,final UserService userService){
        this.loginAttemptService=loginAttemptService;
        this.userService=userService;
    }
 
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent e){
        WebAuthenticationDetails auth = (WebAuthenticationDetails)  e.getAuthentication().getDetails();
         
        loginAttemptService.loginSucceeded(auth.getRemoteAddress());
        User loginUser= null;

            loginUser = userService.getByUsername(e.getAuthentication().getName());


        loginUser.setOnline(true);
        userService.saveRegisteredUser(loginUser);
    }
}
