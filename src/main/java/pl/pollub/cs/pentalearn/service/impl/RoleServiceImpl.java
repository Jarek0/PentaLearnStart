package pl.pollub.cs.pentalearn.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pollub.cs.pentalearn.domain.Role;
import pl.pollub.cs.pentalearn.repository.RoleRepository;
import pl.pollub.cs.pentalearn.repository.UserRepository;
import pl.pollub.cs.pentalearn.service.RoleService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;
import pl.pollub.cs.pentalearn.service.exception.TableIsEmptyException;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    @Inject
    public RoleServiceImpl(final RoleRepository roleRepository,final UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Role save(@NotNull @Valid final Role role) {
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public List<Role> getAllRoles() throws TableIsEmptyException {
        List<Role> roles=roleRepository.findAll();
        if(roles.size()==0)
            throw new TableIsEmptyException("role");
        return roles;
    }

    @Override
    @Transactional
    public Role update(Role role) {
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public Role delete(Role role) {
        roleRepository.delete(role);
        return role;
    }

    @Override
    @Transactional
    public Role getById(Long id) throws NoSuchObjectException {
        Role role=roleRepository.findOne(id);
        if(role == null) throw new NoSuchObjectException(id);
        return role;
    }

    @Override
    @Transactional
    public Role getByName(String rolename)throws NoSuchObjectException {
         Role role=roleRepository.findByName(rolename);
         if(role == null) throw new NoSuchObjectException(rolename);
         return role;
    }

    @Override
    public List<Role> getRolesByUserId(Long userId) {
        return userRepository.findById(userId).getRoles();
    }
}
