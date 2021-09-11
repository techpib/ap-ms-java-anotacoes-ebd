package br.com.techpib.ap.ms_anotacoes_ebd.core.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncoderMD5 {

    public static String encodeToMD5(String texto) throws NoSuchAlgorithmException, NullPointerException {

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(texto.getBytes(), 0, texto.length());
        return new BigInteger(1, messageDigest.digest()).toString(16);

    }

}
