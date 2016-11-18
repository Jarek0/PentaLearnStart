
package pl.pollub.cs.pentalearn.service.listeners;


import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import pl.pollub.cs.pentalearn.domain.User;
import pl.pollub.cs.pentalearn.domain.VerificationToken;
import pl.pollub.cs.pentalearn.repository.VerificationTokenRepository;
import pl.pollub.cs.pentalearn.service.events.OnRegistrationCompleteEvent;
import pl.pollub.cs.pentalearn.service.impl.MailServiceImpl;

import javax.inject.Inject;
import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final VerificationTokenRepository verificationTokenRepository;

    private final MessageSource messages;

    private final MailServiceImpl mailService;

    @Inject
    public RegistrationListener(final VerificationTokenRepository verificationTokenRepository, final MessageSource messages, final MailServiceImpl mailService){
        this.verificationTokenRepository=verificationTokenRepository;
        this.messages=messages;
        this.mailService=mailService;
    }
 
    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }
 
    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        VerificationToken vtoken=new VerificationToken(token,user);
        verificationTokenRepository.save(vtoken);
         
        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = event.getAppUrl() + "/api/registration/confirm?token=" + token;
        String message = messages.getMessage("message.regSucc", null, event.getLocale());
         
        
        mailService.sendMail("from@no-spam.com",recipientAddress,subject,message + " rn " + "http://localhost:8080" + confirmationUrl);
    }
}
