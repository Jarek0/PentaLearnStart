package pl.pollub.cs.pentalearn.service.impl;


import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import pl.pollub.cs.pentalearn.service.MailService;

public class MailServiceImpl implements MailService {
    private MailSender mailSender;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

    @Override
    public void sendMail(String from, String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
    @Override
    public void sendMail(SimpleMailMessage message) {
        
        mailSender.send(message);
    }
    
}

