package io.github.jokoframework.porandu.rest;

/**
 * Created by joko on 06/05/16.
 */
public class ApiPaths {
    /**/
    public static final String ROOT_PORANDU = "/api/porandu";
    /**
     * El objetivo de este mapping es que cada controller tenga su propio diagnóstico, debe tener el path común
     * que precede a las operaciones principales del RestController en cuestión
     */
    public static final String SUFFIX_HEART_BEAT = "/heartbeat";
    public static final String ROOT_QUESTIONS = "/api/questions";
    public static final String ROOT_DIAGNOSTIC = "/diagnostic";
    public static final String QUESTIONS_HEARTBEAT = ROOT_QUESTIONS + SUFFIX_HEART_BEAT;
    public static final String PORANDU_HEARTBEAT = ROOT_PORANDU + SUFFIX_HEART_BEAT;
    public static final String ROOT_ANSWERS = "/api/answers";

    private ApiPaths() {

    }
}
