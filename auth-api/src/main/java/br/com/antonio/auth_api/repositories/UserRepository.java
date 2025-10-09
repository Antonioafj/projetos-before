package br.com.antonio.auth_api.repositories;

import br.com.antonio.auth_api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String > {

    UserDetails findByLogin(String login);

}
