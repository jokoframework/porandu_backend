package io.github.jokoframework.porandu.manager.impl;

import io.github.jokoframework.porandu.entities.ParametroEntity;
import io.github.jokoframework.porandu.enums.TipoParametroEnum;
import io.github.jokoframework.porandu.manager.ParametroManager;
import io.github.jokoframework.porandu.repositories.ParametrosRepository;
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
    private ParametrosRepository parametroRepo;


    private ConcurrentHashMap<String, ParametroEntity> cache = new ConcurrentHashMap<String, ParametroEntity>();


    @Override
    public ParametroManager reload() {
        List<ParametroEntity> list = parametroRepo.findAll();
        cache.clear();
        logger.info("Cargando {} parametros del sistema", list.size());
        for (ParametroEntity p : list) {
            cache.put(p.getNombre(), p);
            logger.trace("Cargado el parámetro: {} - {}", p.getNombre(), p.getValor());
        }
        return this;
    }

    @Override
    public Long getLong(String nombre) {
        ParametroEntity p = getParametro(nombre);
        if (p != null) {
            return Long.valueOf(p.getValor());
        } else {
            throw new IllegalStateException("Parametro requerido " + nombre + " no encontrado");

        }

    }

    private ParametroEntity getParametro(String label) {
        ParametroEntity entity = cache.get(label);
        if (entity != null) {
            return entity;
        }
        return null;
    }

    @Override
    public ParametroManager saveNew(String nombre, String valor, TipoParametroEnum tipo) {
        ParametroEntity p = new ParametroEntity();
        p.setNombre(nombre);
        p.setValor(valor);
        p.setTipo(tipo);
        parametroRepo.save(p);
        return this;
    }

    @Override
    public ParametroManager update(String nombre, String valor) {
        ParametroEntity p = parametroRepo.findByNombre(nombre);
        p.setNombre(nombre);
        p.setValor(valor);
        parametroRepo.save(p);
        return this;
    }

    @Override
    public ParametroManager remove(String nombre) {
        ParametroEntity p = parametroRepo.findByNombre(nombre);
        if (p == null) {
            return this;
        }
        parametroRepo.delete(p);
        return this;
    }
}
