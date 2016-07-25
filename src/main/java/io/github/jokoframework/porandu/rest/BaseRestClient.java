package io.github.jokoframework.porandu.rest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;


/**
 * Created by joko on 18/07/16.
 */
public class BaseRestClient {

    private static Logger LOGGER = Logger.getLogger(BaseRestClient.class);

    private RestTemplate restTemplate;

    @Value("${porandu.joko.http.proxyHost}")
    private String proxyHost="";

    @Value("${porandu.joko.http.proxyPort}")
    private String proxyPort="";

    @PostConstruct
    public void init() {
        HttpClient httpClient = HttpClientBuilder.create().build();
        if (StringUtils.isNotBlank(proxyHost) && StringUtils.isNotBlank(proxyPort)
                && NumberUtils.isNumber(proxyPort)) {
            HttpHost proxyHttp = new HttpHost(proxyHost, Integer.parseInt(proxyPort));
            DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxyHttp);
            httpClient = HttpClients.custom()
                    .setRoutePlanner(routePlanner)
                    .build();
            LOGGER.debug(String.format("Usando proxy: %s:%s", proxyHost, proxyPort));
        }
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        this.restTemplate = new RestTemplate(requestFactory);
        this.restTemplate.setErrorHandler(new PoranduErrorHandler());
    }

    public RestTemplate getRestTemplate() {
        if (restTemplate == null) {
            init();
        }
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate pRestTemplate) {
        restTemplate = pRestTemplate;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String pProxyHost) {
        proxyHost = pProxyHost;
    }

    public String getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(String pProxyPort) {
        proxyPort = pProxyPort;
    }
}
