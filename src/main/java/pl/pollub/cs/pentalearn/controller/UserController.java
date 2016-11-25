package pl.pollub.cs.pentalearn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pollub.cs.pentalearn.domain.User;
import pl.pollub.cs.pentalearn.service.UserService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;
import pl.pollub.cs.pentalearn.service.exception.TableIsEmptyException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    private final UserService userService;

    @Inject
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAllUsers() throws TableIsEmptyException {
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@Valid @RequestBody User user,
                        HttpServletRequest httpServletRequest,
                        HttpServletResponse httpServletResponse) {

        userService.saveRegisteredUser(user);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable Long userId, @Valid @RequestBody User user1,
                           HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse) throws NoSuchObjectException {

        User user = userService.getById(userId);
        user.setUsername(user1.getUsername());
        user.setPassword(user1.getPassword());
        user.setEnabled(user1.getEnabled());
        user.setBanned(user1.getBanned());
        user.setOnline(user1.getOnline());
        user.setRoles(user1.getRoles());
        user.setToken(user1.getToken());
        userService.update(user);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long userId,
                           HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse) throws NoSuchObjectException {

        User user = userService.getById(userId);
        userService.delete(user);
    }
}
