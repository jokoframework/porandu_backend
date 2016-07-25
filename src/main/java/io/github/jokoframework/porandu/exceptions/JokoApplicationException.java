package io.github.jokoframework.porandu.exceptions;

import java.io.IOException;

/**
 * Created by afeltes on 25/07/16.
 */
public class JokoApplicationException extends IOException {
    public JokoApplicationException() {
    }

    public JokoApplicationException(String pS) {
    }

    public JokoApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public JokoApplicationException(Throwable cause) {
        super(cause);
    }

}
