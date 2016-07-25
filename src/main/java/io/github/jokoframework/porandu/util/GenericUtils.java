package io.github.jokoframework.porandu.util;

import com.google.gson.Gson;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Created by joko on 27/05/16.
 */
public class GenericUtils {

    private static final Logger LOGGER = Logger.getLogger(GenericUtils.class);

    private static Gson gson = new Gson();

    private GenericUtils () {
    }

    public static void main(String [] args) {
        BasicConfigurator.configure();
        for(int i=0; i<10; i++) {
            LOGGER.debug(String.format("Waiting: %s", getWaitTimeExponential(i)));
        }
    }

    public static String toJSON(Object pLoginRequest) {
        return gson.toJson(pLoginRequest);
    }

    /*
     * Returns the next wait interval, in milliseconds, using an exponential
     * backoff algorithm.
     */
    public static long getWaitTimeExponential(int retryCount) {
        long waitTime = ((long) Math.pow(2, retryCount) * 100L);
        LOGGER.debug(String.format("Intento %s. Tiempo de Espera: %s", retryCount, waitTime));
        return waitTime;
    }
}
