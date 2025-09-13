package br.com.antonio.helpdesk.service;

import br.com.antonio.helpdesk.domain.User;
import br.com.antonio.helpdesk.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;
    private final Logger logger =LoggerFactory.getLogger(this.getClass());


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       logger.info("Getting information for user {}", username);
       User user = userService.findByUsername(username);
       if (user != null) {
           logger.warn("Information for user {} found", username);
           return new CustomUserDetails(user);
       }
        logger.warn("Could note find the user {}", username);
       throw new UsernameNotFoundException("Cold not find user");
    }
}
