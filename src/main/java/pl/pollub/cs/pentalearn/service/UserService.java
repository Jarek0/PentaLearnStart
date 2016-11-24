package pl.pollub.cs.pentalearn.service;




import pl.pollub.cs.pentalearn.domain.Role;
import pl.pollub.cs.pentalearn.domain.User;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;
import pl.pollub.cs.pentalearn.service.exception.TableIsEmptyException;

import java.util.List;

public interface UserService {

    void saveRegisteredUser(User user);
    
    User registerNewUserAccount(User accountDto);

    User getByUsername(String username);
    
    User getByToken(String verificationToken) throws NoSuchObjectException;
    
    User getByEmail(String email);

    User getById(Long id) throws NoSuchObjectException;

    List<User> getAllUsers() throws TableIsEmptyException;

    User delete(User user);

    User update(User user);


    boolean emailExist(String email);

}
