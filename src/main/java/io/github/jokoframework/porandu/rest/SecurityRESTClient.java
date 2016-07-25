package io.github.jokoframework.porandu.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Esta clase hace la comunicación con los endpoints que tienen la logica para
 * autorizar o no a un usuario
 *
 * @author danicricco
 */
@Component
public class SecurityRESTClient extends BaseRestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityRESTClient.class);

    @Value("${joko.porandu.sec-mod}")
    private String baseURL = "http://echo.jsontest.com/";

    public String getBaseURL() {
        return baseURL;
    }


    protected String urlForPath(final String baseUrl, final String path) {
        String inputPath = path;
        if (inputPath.startsWith("/")) {
            inputPath = inputPath.substring(1);
        }
        return baseUrl + "/" + inputPath;
    }

    protected String urlForPath(final String path) {
        return urlForPath(getBaseURL(), path);
    }

}
