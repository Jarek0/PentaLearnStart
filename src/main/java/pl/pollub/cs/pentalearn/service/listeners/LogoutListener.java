package pl.pollub.cs.pentalearn.service.listeners;


import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pl.pollub.cs.pentalearn.domain.User;
import pl.pollub.cs.pentalearn.service.UserService;

import javax.inject.Inject;
import java.util.List;


@Component
public class LogoutListener implements ApplicationListener<SessionDestroyedEvent> {

    private final UserService userService;

    @Inject
    public LogoutListener(final UserService userService){
        this.userService=userService;
    }
    
    
    @Override
    public void onApplicationEvent(SessionDestroyedEvent event) {
        List<SecurityContext> lstSecurityContext = event.getSecurityContexts();
        UserDetails ud;
        for (SecurityContext securityContext : lstSecurityContext)
        {
            ud = (UserDetails) securityContext.getAuthentication().getPrincipal();
            User logoutUser= null;


                logoutUser = userService.getByUsername(ud.getUsername());


            logoutUser.setOnline(false);
            userService.saveRegisteredUser(logoutUser);
        }
    }
    

}
