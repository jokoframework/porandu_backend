package io.github.jokoframework.porandu.service;


import io.github.jokoframework.porandu.entities.BaseEntity;

/**
 * Created by joko on 22/06/16.
 */
public interface JokoBaseCRUDService {

    BaseEntity findById(Long pId);
}
