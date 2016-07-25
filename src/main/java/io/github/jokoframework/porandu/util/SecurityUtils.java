package io.github.jokoframework.porandu.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @author joko
 */

public class SecurityUtils {

    private static final Logger LOGGER = Logger.getLogger(SecurityUtils.class);

    /**
     * El tipo de algoritmo utilizado para las encriptaciones.
     */
    protected static final String ALGORITHM = "Blowfish";
    private static final String ENCODING = "UTF8";
    private static final int BCRYPT_COMPLEXITY = 6;
    public static final String JOKO_SECRET_KEY = "/opt/porandu/secret.key";
    public static final String AUTH_HEADER_NAME = "X-JOKO-AUTH";
    public static final String API_JOKO_SECURITY_VERSION_HEADER_NAME = "X-JOKO-SECURITY-VERSION";
    public static final String API_JOKO_SECURITY_VERSION = "1.0";


    // Clave por default estática para los encritpados y desencriptados
    // Esto debería actualizarse periódicamente, junto con todos los parámetros
    // que se guarden
    // encriptados con este algoritmo
    // 16 bytes
    private static byte[] defaultKey = new byte[]{11, 38, 27, 32, 65, 21, 73, 66, 91, 78, 98, 97, 19, 95, 94, 90};
    /*
     * *********************************************************
     */

    private SecurityUtils() {

    }

    public static String generateRandomPassword() {
        Random a = new Random();
        a.setSeed(System.currentTimeMillis());
        return String.format("%06d", a.nextInt(999999));
    }

    /**
     * Se encripta un string con una clave.
     *
     * @param message El string a encriptar.
     * @param key     La clave en bytes con la que se quiere encriptar.
     * @return
     */
    public static String encryptarConPassword(String message, byte[] key) {
        String ret = null;
        try {
            Cipher c = Cipher.getInstance(ALGORITHM);
            SecretKeySpec k = new SecretKeySpec(key, ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, k);
            byte[] encrypted = c.doFinal(message.getBytes(ENCODING));
            ret = byteToBase64(encrypted);
        } catch (Exception e) {
            LOGGER.error("No se pudo encriptar la cadena: " + e.getMessage());
        }

        return ret;
    }

    public static String desencryptarConPassword(String encrypted, byte[] key, boolean quiet) {
        String ret = null;
        if (StringUtils.isNotEmpty(encrypted)) {
            ret = desencriptarConKeyByte(encrypted, key, quiet);
        }
        return ret;
    }

    /**
     * Desencripta una cadena con un password.
     *
     * @param encrypted La cadena encriptada y codificada en Base64
     * @param key       La clave en bytes que se utilizará para encriptar.
     * @param quiet     Si se imprimirá o no errores de encriptado. Se puso este
     *                  parámetro, para tener compatibilidad hacia atrás de las
     *                  páginas que ya se tenía con encriptado, cuándo se implement en
     *                  Setiembre de 2011, la encriptación para todos los URLs de la
     *                  aplicación.
     * @return
     */
    private static String desencriptarConKeyByte(String encrypted, byte[] key, boolean quiet) {
        String ret = null;
        try {
            /* El valor encriptado convertido a byte */
            byte[] rawEnc = base64ToByte(encrypted);
            Cipher c = Cipher.getInstance(SecurityUtils.ALGORITHM);
            SecretKeySpec k = new SecretKeySpec(key, SecurityUtils.ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, k);
            byte[] raw = c.doFinal(rawEnc);
            ret = new String(raw, ENCODING);
        } catch (Exception e) {
            if (!quiet) {
                LOGGER.error("No se pudo desencriptar la cadena: " + encrypted, e);
            }
            if (LOGGER.isTraceEnabled()) {
                if (quiet) // solo vuelvo a imprimir si es quiet, porque sino ya
                    // se imprime antes
                    LOGGER.trace("No se pudo desencriptar la cadena: " + encrypted);
                LOGGER.trace("\tclave: " + new String(key, Charset.defaultCharset()));
            }
        }
        return ret;
    }

    public static String desencryptarConPassword(String encrypted, byte[] key) {
        return desencryptarConPassword(encrypted, key, false);
    }

    /**
     * From a byte[] returns a base 64 representation
     *
     * @param data byte[]
     * @return String
     * @throws IOException
     */
    public static String byteToBase64(byte[] data) {

        return DatatypeConverter.printBase64Binary(data);

    }

    /**
     * From a base 64 representation, returns the corresponding byte[]
     *
     * @param data String The base64 representation
     * @return byte[]
     * @throws IOException
     */
    public static byte[] base64ToByte(String data) {

        return DatatypeConverter.parseBase64Binary(data);
    }

    /**
     * Encripta una cadena con el defaultKey
     *
     * @param message
     * @return
     */
    public static String encrypt(String message) {
        return encryptarConPassword(message, SecurityUtils.defaultKey);
    }

    /**
     * Desencripta una cadena que se encript� con el defaultKey
     *
     * @param encrypted
     * @return
     */
    public static String decrypt(String encrypted) {
        return desencryptarConPassword(encrypted, SecurityUtils.defaultKey);
    }


    public static void habilitarLogSQL() {
        setCategoriaLogLevel("org.hibernate.SQL", Level.DEBUG);
        setCategoriaLogLevel("org.hibernate.type", Level.TRACE);
    }

    public static void deshabilitarLogSQL() {
        setCategoriaLogLevel("org.hibernate.SQL", Level.WARN);
        setCategoriaLogLevel("org.hibernate.type", Level.WARN);
    }

    public static void setCategoriaLogLevel(String categoria, Level level) {
        org.apache.log4j.Logger.getLogger(categoria).setLevel(level);
    }

    public static void setHibernateLogLevel(String categoria, Level level) {
        LOGGER.trace("Hibernate level a: " + level);
        setCategoriaLogLevel("org.hibernate.SQL", level);
        setCategoriaLogLevel("org.hibernate.type", level);
    }

    public static String sha256(String payload) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(payload.getBytes("UTF-8"));
            return byteToBase64(messageDigest.digest());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * Lee todos los bytes de un archivo en particular y lo convierte a un
     * string en Base64
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String readFileToBase64(String filePath) throws IOException {
        Path path = FileSystems.getDefault().getPath(filePath);
        byte[] bytesFromFile = Files.readAllBytes(path);
        return byteToBase64(bytesFromFile);
    }
}
