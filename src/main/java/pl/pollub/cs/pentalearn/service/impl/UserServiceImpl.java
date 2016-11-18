
package pl.pollub.cs.pentalearn.service.impl;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pollub.cs.pentalearn.domain.Role;
import pl.pollub.cs.pentalearn.domain.User;
import pl.pollub.cs.pentalearn.repository.RoleRepository;
import pl.pollub.cs.pentalearn.repository.UserRepository;
import pl.pollub.cs.pentalearn.repository.VerificationTokenRepository;
import pl.pollub.cs.pentalearn.service.UserService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;
import pl.pollub.cs.pentalearn.service.exception.TableIsEmptyException;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final VerificationTokenRepository tokenRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Inject
    public UserServiceImpl(final UserRepository userRepository, final RoleRepository roleRepository, final VerificationTokenRepository tokenRepository, final BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.tokenRepository = tokenRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public void saveRegisteredUser(@NotNull final User user) {
        
        userRepository.save(user);
    }


    @Override
    @Transactional
    public User registerNewUserAccount(User accountDto){

            //Walidacja danych po rejestracji

            
            accountDto.setPassword(bCryptPasswordEncoder.encode(accountDto.getPassword()));
            accountDto.setRoles(new ArrayList<Role>(Arrays.asList(roleRepository.findByName("USER"))));
            accountDto.setEnabled(false);
            accountDto.setBanned(false);
            accountDto.setOnline(false);
            User savedUser=userRepository.save(accountDto);
            return savedUser;
    }

    @Transactional
    @Override
    public User getByUsername(String username){
            User user=userRepository.findByUsername(username);

            return user;
    }

    @Transactional
    @Override
    public User getByToken(String verificationToken) throws NoSuchObjectException {
            User user=tokenRepository.findByToken(verificationToken).getUser();
            if(user==null)
                throw new NoSuchObjectException("no user with verification token: "+verificationToken);
            return user;
    }

    @Transactional
    @Override
    public User getByEmail(String email){
        User user=userRepository.findByEmail(email);

        return user;
    }

    @Transactional
    @Override
    public User getById(Long id) throws NoSuchObjectException {
        User user=userRepository.findById(id);
        if(user==null)
            throw new NoSuchObjectException(id);
        return user;
    }

    @Transactional
    @Override
    public boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public List<User> getAllUsers() throws TableIsEmptyException {
        List<User> users=userRepository.findAll();
        if(users.size()==0)
            throw new TableIsEmptyException("user");
        return users;
    }

    @Transactional
    @Override
    public User delete(User user) {
        userRepository.delete(user);
        return user;
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public void addUserRole(User user, Role role){
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void addUserRoles(User user, List<Role> roles){
        user.getRoles().addAll(roles);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUserRole(User user, Role role){
        user.getRoles().remove(role);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUserRoles(User user, List<Role> roles){
        user.getRoles().removeAll(roles);
        userRepository.save(user);
    }
    
}
