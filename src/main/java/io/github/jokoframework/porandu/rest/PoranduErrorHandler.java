package io.github.jokoframework.porandu.rest;

import io.github.jokoframework.porandu.exceptions.JokoApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class PoranduErrorHandler implements ResponseErrorHandler {

    public boolean hasError(ClientHttpResponse response) throws IOException {
        //io.github.jokoframework.security.controller.AuthenticationController.processLoginSucessfull() está devolviendo
        // status 200
        if (HttpStatus.ACCEPTED.equals(response.getStatusCode()) ||
                HttpStatus.OK.equals(response.getStatusCode())
                || HttpStatus.UNAUTHORIZED.equals(response.getStatusCode())) {
            return false;
        }
        return true;
    }

    public void handleError(ClientHttpResponse response) throws IOException {
        throw new JokoApplicationException("Error inesperado durante la autenticación. " + response.getStatusText());

    }

}
