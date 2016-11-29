
package pl.pollub.cs.pentalearn.service.listeners;


import org.springframework.context.ApplicationListener;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;
import pl.pollub.cs.pentalearn.service.events.BanUserEvent;

import javax.inject.Inject;
import java.util.List;


@Component
public class BanUserListener implements ApplicationListener<BanUserEvent> {

    private final SessionRegistry sessionRegistry;

    @Inject
    public BanUserListener(final SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }


    @Override
    public void onApplicationEvent(BanUserEvent event) {
        try {
            this.confirmBan(event);
        } catch (Exception ex) {
        }
    }

    private void confirmBan(BanUserEvent event) throws Exception {
        String bannedUser = event.getUserName();
        if (event.getOnline()) {
            org.springframework.security.core.userdetails.User bannedUserWithSession = findUserByUsername(bannedUser);
            if (bannedUserWithSession != null) {
                for (SessionInformation session : (sessionRegistry.getAllSessions(bannedUserWithSession, false))) {
                    session.expireNow();
                }
            }
        }

    }

    public org.springframework.security.core.userdetails.User findUserByUsername(String userName) {
        List<Object> onlineUserList = sessionRegistry.getAllPrincipals();
        for (Object onlineUser : onlineUserList) {
            org.springframework.security.core.userdetails.User convertedonlineUser = (org.springframework.security.core.userdetails.User) onlineUser;
            if (((convertedonlineUser).getUsername()).equalsIgnoreCase(userName)) {
                return convertedonlineUser;
            }

        }
        return null;
    }

}
