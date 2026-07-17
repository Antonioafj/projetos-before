package authentication.authentication.security;

import authentication.authentication.modules.user.UserRepository;
import authentication.authentication.modules.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetails implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User exisUser = userRepository.findByUsername(username);

        if (exisUser != null){
             throw new Error(("User already exists!"));
        }

        return null;
    }
}
