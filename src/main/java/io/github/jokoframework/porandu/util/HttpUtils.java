package io.github.jokoframework.porandu.util;

import io.github.jokoframework.porandu.constantes.ApplicationConstants;
import org.springframework.http.HttpHeaders;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by joko on 08/06/16.
 */
public class HttpUtils {

    private HttpUtils() {

    }


    public static String getFirstHeaderValue(HttpHeaders pHeaders, String pHeaderName) {
        String value = null;
        List<String> allValues = getHeadersValue(pHeaders, pHeaderName);
        if (allValues != null && !allValues.isEmpty()) {
            value = allValues.get(0);
        }
        return value;
    }

    public static List<String> getHeadersValue(HttpHeaders pHeaders, String pHeaderName) {
        List<String> values = null;
        if (pHeaders != null) {
            values = pHeaders.get(pHeaderName);
        }
        if (values == null) {
            values = new ArrayList<>();
        }
        return values;
    }


    public static String getSecurityToken(ServletRequest pServletRequest) {
        String token = null;
        if (pServletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) pServletRequest;
            token = httpServletRequest.getHeader(ApplicationConstants.AUTH_HEADER_NAME);
        }
        return token;
    }

    public enum VERBS {DELETE, GET, PATCH, POST}
}
