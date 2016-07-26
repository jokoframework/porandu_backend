package io.github.jokoframework.porandu;

import javax.annotation.PostConstruct;

import io.github.jokoframework.porandu.manager.ParametroManager;
import org.flywaydb.core.api.callback.FlywayCallback;
import org.flywaydb.core.internal.callback.SqlScriptFlywayCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
/**
 * <p>
 * Esto componente resume el startup de Porandu y el orden en que se deben llamar
 * </p>
 * <p>
 * En lugar de utilizar @PostConstruct a lo largo del código, se utiliza solo
 * dentro de esta clase para facilitar la lectura
 * </p>
 * 
 * @author danicricco
 *
 */
public class StartupSetup {

    private static final Logger logger = LoggerFactory.getLogger(StartupSetup.class);

    @Autowired
    private ParametroManager parametroManager;

    @PostConstruct
    public void init() {
        if(parametroManager.count() <=0L) {

        }
        parametroManager.reload();
        logger.info("Porandu inicializado correctamente");
    }
}
