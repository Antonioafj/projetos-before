package br.com.antonio.simpletwiter.config;

import br.com.antonio.simpletwiter.entities.Role;
import br.com.antonio.simpletwiter.entities.User;
import br.com.antonio.simpletwiter.repository.RoleRepository;
import br.com.antonio.simpletwiter.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Configuration
public class AdmininUserConfig implements CommandLineRunner {

    private RoleRepository roleRepository;

    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public AdmininUserConfig(RoleRepository roleRepository,
                             UserRepository userRepository,
                             BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {


        var roleAdminOptional = roleRepository.findByName(Role.Values.ADMIN.name());

        var roleAdmin = roleAdminOptional.orElseGet(() -> {
            var newRole = new Role(Role.Values.ADMIN.name());
            return roleRepository.save(newRole);
        });

        var userAdmin = userRepository.findByUsername("admin");

        userAdmin.ifPresentOrElse(user -> System.out.println("admin ja existe"),
                () -> {
                    var user = new User();
                    user.setUsername("admin");
                    user.setPassword(passwordEncoder.encode("123"));
                    user.setRoles(Set.of(roleAdmin));
                    userRepository.save(user);
                }
        );

    }
}





























