package pl.pollub.cs.pentalearn;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import pl.pollub.cs.pentalearn.service.impl.MailServiceImpl;

import java.util.Locale;
import java.util.Properties;

@Configuration
@EnableWebMvc
public class MailConfig {


    @Bean
    public JavaMailSenderImpl mailSender(){
        JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPassword("STALKER70A3");
        mailSender.setUsername("testaccont666@gmail.com");
        mailSender.setPort(587);
        Properties props = new Properties();
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
        mailSender.setJavaMailProperties(props);
        return mailSender;
    }

    @Bean
    public MailServiceImpl mailService(){
        MailServiceImpl mailService=new MailServiceImpl();
        mailService.setMailSender(mailSender());
        return mailService;
    }

    @Bean
    public ResourceBundleMessageSource messageSource(){
        ResourceBundleMessageSource messageSource=new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }
    @Bean
    public SessionLocaleResolver sessionLocaleResolver(){
        SessionLocaleResolver sessionLocaleResolver=new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(new Locale("en"));
        return sessionLocaleResolver;
    }
}
