package pl.pollub.cs.pentalearn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pollub.cs.pentalearn.domain.Role;
import pl.pollub.cs.pentalearn.domain.User;
import pl.pollub.cs.pentalearn.service.RoleService;
import pl.pollub.cs.pentalearn.service.UserService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;
import pl.pollub.cs.pentalearn.service.exception.ObjectHasNoItemsInTableException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class RoleController {
    private final RoleService roleService;
    private final UserService userService;

    @Inject
    public RoleController(final RoleService roleService, final UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @RequestMapping(value = "/roles/{roleId}", method = RequestMethod.GET)
    public List<Role> showRolesById(@PathVariable Long roleId) throws NoSuchObjectException, ObjectHasNoItemsInTableException {
        return roleService.getRolesByUserId(roleId);
    }

    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addRole(@Valid @RequestBody Role role,
                        HttpServletRequest httpServletRequest,
                        HttpServletResponse httpServletResponse) {

        roleService.save(role);
    }

    @RequestMapping(value = "/roles/{roleId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateRole(@PathVariable Long roleId, @Valid @RequestBody Role role1,
                           HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse) throws NoSuchObjectException {

        Role role = roleService.getById(roleId);
        role.setName(role1.getName());
        roleService.update(role);
    }

    @RequestMapping(value = "/roles/{roleId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteRole(@PathVariable Long roleId,
                           HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse) throws NoSuchObjectException {

        Role role = roleService.getById(roleId);
        roleService.delete(role);
    }

    @RequestMapping(value = "/users/{userId}/roles", method = RequestMethod.GET)
    public List<Role> showRolesByUserId(@PathVariable Long userId) throws NoSuchObjectException, ObjectHasNoItemsInTableException {
        return userService.getById(userId).getRoles();
    }

    @RequestMapping(value = "/users/{userId}/roles/{roleId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addRoleToUser(@PathVariable Long userId, @PathVariable Long roleId,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws NoSuchObjectException {

        User user = userService.getById(userId);
        Role role = roleService.getById(roleId);
        user.getRoles().add(role);

        userService.saveRegisteredUser(user);
    }


    @RequestMapping(value = "/users/{userId}/roles/{roleId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteRoleInUser(@PathVariable Long userId, @PathVariable Long roleId,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse) throws NoSuchObjectException {

        User user = userService.getById(userId);
        Role role = roleService.getById(roleId);
        user.getRoles().remove(role);

        userService.saveRegisteredUser(user);
    }
}
