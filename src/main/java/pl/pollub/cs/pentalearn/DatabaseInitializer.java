package pl.pollub.cs.pentalearn;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.pollub.cs.pentalearn.domain.Role;
import pl.pollub.cs.pentalearn.domain.User;
import pl.pollub.cs.pentalearn.repository.RoleRepository;
import pl.pollub.cs.pentalearn.repository.UserRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class DatabaseInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Inject
    DatabaseInitializer(final UserRepository userRepository, final RoleRepository roleRepository, final BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (roleRepository.findByName("ROOT") == null) {
            Role root = new Role("ROOT", null);
            roleRepository.save(root);

        }
        if (roleRepository.findByName("USER") == null) {
            Role user = new Role("USER", null);
            roleRepository.save(user);
        }
        if (roleRepository.findByName("TEACHER") == null) {
            Role teacher = new Role("TEACHER", null);
            roleRepository.save(teacher);
        }
        if (userRepository.findByUsername("rootUser") == null) {
            User rootUser = new User("rootUser", bCryptPasswordEncoder.encode("poziomd123"), null, "6darksavant9@gmail.com", true, false, false, null, new ArrayList<Role>(Arrays.asList(roleRepository.findByName("ROOT"))));
            userRepository.save(rootUser);
        }
    }
}
