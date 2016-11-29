
package pl.pollub.cs.pentalearn.controller;


import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import pl.pollub.cs.pentalearn.domain.User;
import pl.pollub.cs.pentalearn.domain.VerificationToken;
import pl.pollub.cs.pentalearn.service.UserService;
import pl.pollub.cs.pentalearn.service.VerificationTokenService;
import pl.pollub.cs.pentalearn.service.events.OnRegistrationCompleteEvent;
import pl.pollub.cs.pentalearn.service.exception.AuthException;
import pl.pollub.cs.pentalearn.service.exception.InvalidRequestException;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;
import pl.pollub.cs.pentalearn.service.validator.UserValidator;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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

    //private final MailServiceImpl mailService;

    private final UserValidator userValidator;

    @Inject
    public SecurityController(final MessageSource messages, final ApplicationEventPublisher eventPublisher, final UserService userService, final VerificationTokenService verificationTokenService, final UserValidator userValidator) {
        this.messages = messages;
        this.eventPublisher = eventPublisher;
        this.userService = userService;
        this.verificationTokenService = verificationTokenService;
        //this.mailService=mailService;
        this.userValidator = userValidator;
    }

    /*@InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }*/


    /*Żeby się zarejestrować trzeba wysłać JSON postaci:
    {
    "username":"username"
    "password":"password"
    "passwordConfirm":"passwordConfirm"
    "email":"email"
    }
    Requestem /api/registration POST

    Żeby się zalogować należy wysłać Request /auth/login_check z username i password opcjonalnie remember-me
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void registration(@RequestBody @Valid User userForm, HttpServletRequest request, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid user", bindingResult);
        }
        userService.registerNewUserAccount(userForm);
        String appUrl = request.getContextPath();
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(userForm, request.getLocale(), appUrl));
    }

    //potwierdzenie wysyalne jest na maila. Link wysłany w mailu uruchamia procedurę weryfikacji
    @RequestMapping(value = "/registration/confirm", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void confirmRegistration(WebRequest request, @RequestParam("token") String token) throws NoSuchObjectException, AuthException {
        Locale locale = request.getLocale();

        VerificationToken verificationToken = verificationTokenService.getByToken(token);
        if (verificationToken == null) {
            throw new AuthException(messages.getMessage("auth.message.invalidToken", null, locale));
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getDate() - cal.getTime().getTime()) <= 0) {
            throw new AuthException(messages.getMessage("auth.message.expired", null, locale));
        }

        user.setEnabled(true);
        userService.saveRegisteredUser(user);

    }


}
