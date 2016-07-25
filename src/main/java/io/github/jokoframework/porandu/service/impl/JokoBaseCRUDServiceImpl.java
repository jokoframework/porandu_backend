package io.github.jokoframework.porandu.service.impl;

import io.github.jokoframework.porandu.entities.BaseEntity;
import io.github.jokoframework.porandu.service.JokoBaseCRUDService;
import org.apache.log4j.Logger;

/**
 * Created by joko on 22/06/16.
 */
public class JokoBaseCRUDServiceImpl implements JokoBaseCRUDService {

    private static final Logger LOGGER = Logger.getLogger(JokoBaseCRUDServiceImpl.class);

    @Override
    public BaseEntity findById(Long pId) {
        return null;
    }
}
