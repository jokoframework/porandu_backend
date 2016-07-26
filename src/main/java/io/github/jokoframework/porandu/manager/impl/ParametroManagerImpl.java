package io.github.jokoframework.porandu.manager.impl;

import io.github.jokoframework.porandu.entities.ParametersEntity;
import io.github.jokoframework.porandu.enums.TipoParametroEnum;
import io.github.jokoframework.porandu.manager.ParametroManager;
import io.github.jokoframework.porandu.repositories.ParametersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ParametroManagerImpl implements ParametroManager {

    private static final Logger logger = LoggerFactory.getLogger(ParametroManagerImpl.class);

    @Resource
    private ParametersRepository parametroRepo;


    private ConcurrentHashMap<String, ParametersEntity> cache = new ConcurrentHashMap<String, ParametersEntity>();


    @Override
    public ParametroManager reload() {
        List<ParametersEntity> list = parametroRepo.findAll();
        cache.clear();
        logger.info("Cargando {} parametros del sistema", list.size());
        for (ParametersEntity p : list) {
            cache.put(p.getName(), p);
            logger.trace("Cargado el parámetro: {} - {}", p.getName(), p.getValue());
        }
        return this;
    }

    @Override
    public Long getLong(String nombre) {
        ParametersEntity p = getParametro(nombre);
        if (p != null) {
            return Long.valueOf(p.getValue());
        } else {
            throw new IllegalStateException("Parametro requerido " + nombre + " no encontrado");

        }

    }

    private ParametersEntity getParametro(String label) {
        ParametersEntity entity = cache.get(label);
        if (entity != null) {
            return entity;
        }
        return null;
    }

    @Override
    public ParametroManager saveNew(String nombre, String valor, TipoParametroEnum tipo) {
        ParametersEntity p = new ParametersEntity();
        p.setName(nombre);
        p.setValue(valor);
        p.setType(tipo);
        parametroRepo.save(p);
        return this;
    }

    @Override
    public ParametroManager update(String nombre, String valor) {
        ParametersEntity p = parametroRepo.findByName(nombre);
        p.setName(nombre);
        p.setValue(valor);
        parametroRepo.save(p);
        return this;
    }

    @Override
    public ParametroManager remove(String nombre) {
        ParametersEntity p = parametroRepo.findByName(nombre);
        if (p == null) {
            return this;
        }
        parametroRepo.delete(p);
        return this;
    }
}
