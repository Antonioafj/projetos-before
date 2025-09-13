package br.com.antonio.helpdesk.service;

import br.com.antonio.helpdesk.domain.User;
import br.com.antonio.helpdesk.entity.UserEntity;
import br.com.antonio.helpdesk.exceptions.BusinessException;
import br.com.antonio.helpdesk.exceptions.ConflictException;
import br.com.antonio.helpdesk.mapper.UserMapper;
import br.com.antonio.helpdesk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.sqm.tree.expression.ValueBindJpaCriteriaParameter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper mapper;

    public User createUser(User newUser){

        Optional<UserEntity> existentUser = userRepository.findByUsername(newUser.getUsername());
        if (existentUser.isPresent()) {
            throw new ConflictException("This username is already in use in the system");
        }

        UserEntity entity = mapper.toEntity(newUser);
        entity.setCreateAt(new Date());
        entity.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
        entity = userRepository.save(entity);
        return mapper.toDomain(entity);

    }

    public User findByUsername(String username) {

        UserEntity entity = userRepository.findByUsername(username).orElse(null);
        if (entity == null) {
            throw new BusinessException("User not found");
        }
        return mapper.toDomain(entity);
    }
}



























