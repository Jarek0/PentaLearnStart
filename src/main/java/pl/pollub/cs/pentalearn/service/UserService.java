package pl.pollub.cs.pentalearn.service;

import pl.pollub.cs.pentalearn.domain.User;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> getList();

}
