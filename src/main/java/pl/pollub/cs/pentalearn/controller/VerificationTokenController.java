package pl.pollub.cs.pentalearn.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pollub.cs.pentalearn.domain.*;
import pl.pollub.cs.pentalearn.service.UserService;
import pl.pollub.cs.pentalearn.service.VerificationTokenService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;
import pl.pollub.cs.pentalearn.service.exception.ObjectHasNoItemsInTableException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/users/{userId}/tokens")
public class VerificationTokenController {
    private final UserService userService;
    private final VerificationTokenService verificationTokenService;

    @Inject
    VerificationTokenController(final UserService userService, final VerificationTokenService verificationTokenServiceService){
        this.userService = userService;
        this.verificationTokenService = verificationTokenServiceService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public VerificationToken showTokenByUserId(@PathVariable Long userId) throws NoSuchObjectException, ObjectHasNoItemsInTableException {
        return userService.getById(userId).getToken();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addToken(@PathVariable Long userId,@Valid @RequestBody VerificationToken token1,
                           HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse) throws NoSuchObjectException {


        VerificationToken token=new VerificationToken(token1.getToken(),userService.getById(userId));

        verificationTokenService.save(token);
    }

    @RequestMapping(value = "/{tokenId}",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateToken(@PathVariable Long tokenId,@Valid @RequestBody VerificationToken token1,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws NoSuchObjectException {

        VerificationToken token=verificationTokenService.getById(tokenId);
        token.setToken(token1.getToken());
        token.setVerified(token1.getVerified());
        token.setDate(token1.getDate());
        verificationTokenService.update(token);
    }

    @RequestMapping(value = "/{tokenId}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteChapter(@PathVariable Long tokenId,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws NoSuchObjectException {

        VerificationToken token=verificationTokenService.getById(tokenId);

        verificationTokenService.delete(token);
    }
}
