package pl.pollub.cs.pentalearn.service;


import pl.pollub.cs.pentalearn.domain.Role;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;
import pl.pollub.cs.pentalearn.service.exception.TableIsEmptyException;

import java.util.List;

public interface RoleService {

    Role save(Role role) ;
    List<Role> getAllRoles() throws TableIsEmptyException;
    Role update(Role role);
    Role delete(Role role);
    Role getById(Long id)throws NoSuchObjectException;
    Role getByName(String rolename)throws NoSuchObjectException;
}
