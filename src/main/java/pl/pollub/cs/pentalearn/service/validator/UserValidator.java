
package pl.pollub.cs.pentalearn.service.validator;


import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.pollub.cs.pentalearn.domain.User;
import pl.pollub.cs.pentalearn.service.UserService;

import javax.inject.Inject;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    private final UserService userService;

    private final MessageSource messages;

    @Inject
    public UserValidator(final MessageSource messages, final UserService userService) {
        this.userService = userService;
        this.messages = messages;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
//tutaj powinien pobierac locale z request ale nasza aplikacja na razie nie obsluguje takich rzeczy jak locale xD
        Locale locale = new Locale("");
        if (userService.emailExist(user.getEmail())) {
            errors.rejectValue("email", "Email.exist", messages.getMessage("Email.exist", null, locale));
        }


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username", messages.getMessage("Size.userForm.username", null, locale));
        }


        if (userService.getByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username", messages.getMessage("Duplicate.userForm.username", null, locale));

        }


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password", messages.getMessage("Size.userForm.password", null, locale));
        }

        System.out.println(user.getPasswordConfirm());
        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm", messages.getMessage("Diff.userForm.passwordConfirm", null, locale));
        }
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        matcher = pattern.matcher(user.getEmail());
        if (!(matcher.matches())) {
            errors.rejectValue("email", "BadForm.userForm.email", messages.getMessage("BadForm.userForm.email", null, locale));
        }


    }
}
