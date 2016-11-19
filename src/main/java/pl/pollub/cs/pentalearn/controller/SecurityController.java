
package pl.pollub.cs.pentalearn.controller;


import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import pl.pollub.cs.pentalearn.GenericResponse;
import pl.pollub.cs.pentalearn.domain.User;
import pl.pollub.cs.pentalearn.domain.VerificationToken;
import pl.pollub.cs.pentalearn.service.UserService;
import pl.pollub.cs.pentalearn.service.VerificationTokenService;
import pl.pollub.cs.pentalearn.service.events.OnRegistrationCompleteEvent;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;
import pl.pollub.cs.pentalearn.service.impl.MailServiceImpl;
import pl.pollub.cs.pentalearn.service.validator.UserValidator;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;


@RestController
@RequestMapping(value = "/api")
public class SecurityController {


    private final MessageSource messages;

    private final ApplicationEventPublisher eventPublisher;

    private final UserService userService;

    private final VerificationTokenService verificationTokenService;

    private final MailServiceImpl mailService;

    private final UserValidator userValidator;

    @Inject
    public SecurityController(final MessageSource messages, final ApplicationEventPublisher eventPublisher, final UserService userService, final VerificationTokenService verificationTokenService, final MailServiceImpl mailService,final UserValidator userValidator)
    {
        this.messages=messages;
        this.eventPublisher=eventPublisher;
        this.userService=userService;
        this.verificationTokenService=verificationTokenService;
        this.mailService=mailService;
        this.userValidator=userValidator;
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public void registration(@RequestBody @Valid User userForm, HttpServletRequest request,BindingResult bindingResult){


        User registered = userService.registerNewUserAccount(userForm);

        String appUrl = request.getContextPath();
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(userForm, request.getLocale(), appUrl));
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request, HttpServletResponse response, Model model){

        if (error != null) {

            String targetUrl = getRememberMeTargetUrlFromSession(request);
            if(StringUtils.hasText(targetUrl)){
                //to do komunikaty o błędach w logowaniu
            }

        }


        if (logout != null) {
            //to do komunikat o wylogowaniu
        }


        //"Your csrf token is in header of response. You can start procedure of login. Please send request '/auth/login_check' method 'Post' with parameters: username, password";

    }
    
    @RequestMapping(value = "/registration/confirm", method = RequestMethod.GET)
    public void confirmRegistration(WebRequest request, @RequestParam("token") String token) throws NoSuchObjectException {
    Locale locale = request.getLocale();
     
    VerificationToken verificationToken = verificationTokenService.getByToken(token);
    if (verificationToken == null) {
        String message = messages.getMessage("auth.message.invalidToken", null, locale);
        //to do komunikat o o nieprawidłowym tokenie nie działa
    }
     
    User user = verificationToken.getUser();
    Calendar cal = Calendar.getInstance();
    if ((verificationToken.getDate() - cal.getTime().getTime()) <= 0) {
        String messageValue = messages.getMessage("auth.message.expired", null, locale);
        //to do komunikat o przeterminowanym kodzie nie działa
    } 
     
    user.setEnabled(true); 
    userService.saveRegisteredUser(user);
    //"Congratulation. Your registration is completed. You can login to your account sending request '/auth/login_check' with username and password as parameters";
}

//to do ponowienie wysyłania tokenu do zrobienia
    @RequestMapping(value = "/user/resendRegistrationToken", method = RequestMethod.GET)
    @ResponseBody
    public GenericResponse resendRegistrationToken(HttpServletRequest request, @RequestParam("token") String existingToken) throws NoSuchObjectException {
        VerificationToken newToken = verificationTokenService.generateNewVerificationToken(existingToken);
        User user = userService.getByToken(newToken.getToken());
        String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        SimpleMailMessage email = constructResendVerificationTokenEmail(appUrl, request.getLocale(), newToken, user);
        mailService.sendMail(email);

        return new GenericResponse(messages.getMessage("message.resendToken", null, request.getLocale()));
    }

//to do strona wyświetlana w przypadku zbanowania przez roota
    @RequestMapping(value = "/youarebanned", method = RequestMethod.GET)
    public String youAreBanned()
    {
        return "you are banned";
    }


    //to do to co ma się pojawiać gdy zalogujesz się jako user
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public void AuthSuccessAsUser(HttpServletRequest request,HttpServletResponse response,Authentication auth)
    {
        if(auth.isAuthenticated()) {
            CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
            response.setHeader("X-CSRF-HEADER", token.getHeaderName());
            response.setHeader("X-CSRF-PARAM", token.getParameterName());
            response.setHeader("X-CSRF-TOKEN", token.getToken());
        }

    }

    //to do strona błędu 403
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accesssDenied() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            //to do
        }
        return "403";

    }




    private String getRememberMeTargetUrlFromSession(HttpServletRequest request){
        String targetUrl = "";
        HttpSession session = request.getSession(false);
        if(session!=null){
            targetUrl = session.getAttribute("targetUrl")==null?""
                    :session.getAttribute("targetUrl").toString();
        }
        return targetUrl;
    }

    //to do funkcja wysyłająca ponownie token
    private SimpleMailMessage constructResendVerificationTokenEmail(String contextPath, Locale locale, VerificationToken newToken, User user) {
        String confirmationUrl = contextPath + "/regitrationConfirm.html?token=" + newToken.getToken();
        String message = messages.getMessage("message.resendToken", null, locale);
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject("Resend Registration Token");
        email.setText(message + " rn" + confirmationUrl);
        email.setFrom("from@no-spam.com");
        email.setTo(user.getEmail());
        return email;
    }


}
