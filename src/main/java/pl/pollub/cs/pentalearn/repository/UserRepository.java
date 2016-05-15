package pl.pollub.cs.pentalearn.repository;

import pl.pollub.cs.pentalearn.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
