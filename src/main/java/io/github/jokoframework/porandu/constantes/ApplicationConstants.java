package io.github.jokoframework.porandu.constantes;

/**
 * Created by joko on 07/06/16.
 */
public class ApplicationConstants {

    public static final long ONE_SECOND = 1000;
    public static final long ONE_MINUTE = ONE_SECOND * 60;
    public static final long ONE_HOUR = ONE_MINUTE * 60;
    public static final long ONE_DAY = ONE_HOUR * 24;

    public static final long MAX_WAIT_INTERVAL = ONE_DAY;

    public static final int MAX_RETRIES = 30;
    public static final String AUTH_HEADER_NAME = "X-JOKO-AUTH";
    public static final String DEFAULT_ROLE = "porandu";
    public static final String VERSION_HEADER_NAME = "X-PORANDU-API-VERSION";
    public static final String VERSION_HEADER_VALUE = "1.0";

    private ApplicationConstants() {
    }

}
