package io.github.jokoframework.porandu.exceptions;

/**
 * Created by afeltes on 04/08/16.
 */
public class PoranduValueRequiredException extends RuntimeException {
    public PoranduValueRequiredException() {
        //default constructor
    }

    public PoranduValueRequiredException(String message) {
        super(message);
    }

    public PoranduValueRequiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public PoranduValueRequiredException(Throwable cause) {
        super(cause);
    }

    public PoranduValueRequiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
