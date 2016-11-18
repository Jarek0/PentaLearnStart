package pl.pollub.cs.pentalearn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import pl.pollub.cs.pentalearn.domain.Role;
import pl.pollub.cs.pentalearn.domain.User;
import pl.pollub.cs.pentalearn.repository.UserRepository;
import pl.pollub.cs.pentalearn.service.LoginAttemptService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private LoginAttemptService loginAttemptService;


    @Autowired
    private HttpServletRequest request;


    private String getClientIP() {
    String xfHeader = request.getHeader("X-Forwarded-For");
    if (xfHeader == null){
        return request.getRemoteAddr();
    }
    return xfHeader.split(",")[0];
}
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException,DisabledException,BadCredentialsException,RuntimeException {
        
    /*    String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }*/
        
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        boolean enabled = true;
       if(!user.getEnabled())
        {
            enabled = false;
            throw new DisabledException("not verified");
        }

        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = !user.getBanned();
        if (!accountNonLocked) {
            throw new RuntimeException("banned");
        }
        
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
    }
}

