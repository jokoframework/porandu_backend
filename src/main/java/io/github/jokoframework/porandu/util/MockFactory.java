package io.github.jokoframework.porandu.util;

import java.util.Random;

/**
 * Esta clase es utilizada para generar stubs para que el front end pueda iniciar el desarrollo.
 * Más adelante servirá para los Unit Test
 * <p>
 * Created by joko on 12/05/16.
 */
public class MockFactory {

    private static Random randomInteger = new Random(System.currentTimeMillis());

    private MockFactory() {
    }

}
