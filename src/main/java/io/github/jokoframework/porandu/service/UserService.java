package io.github.jokoframework.porandu.service;

import io.github.jokoframework.porandu.entities.UserEntity;

/**
 * Created by agimenez on 02/08/16.
 */
public interface UserService {
    UserEntity getOrInsertUser(String userName);
}
