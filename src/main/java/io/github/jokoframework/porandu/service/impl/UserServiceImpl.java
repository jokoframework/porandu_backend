package io.github.jokoframework.porandu.service.impl;

import io.github.jokoframework.porandu.entities.UserEntity;
import io.github.jokoframework.porandu.repositories.UserRepository;
import io.github.jokoframework.porandu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author agimenez
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserEntity getOrInsertUser(String userName) {
        UserEntity userEntity = repository.findByUserName(userName);

        if (userEntity == null) {
            userEntity = new UserEntity();
            userEntity.setUserName(userName);
            repository.save(userEntity);
        }

        return userEntity;
    }
}
