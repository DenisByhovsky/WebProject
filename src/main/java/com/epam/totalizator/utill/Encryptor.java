package com.epam.totalizator.utill;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor{

    private static final Logger LOGGER = LogManager.getLogger(Encryptor.class.getName());

    public static String sha1Encrypt(String password) {
        String sha1 = null;
        try {
            MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");

            msdDigest.update(password.getBytes("UTF-8"), 0, password.length());
            sha1 = DatatypeConverter.printHexBinary(msdDigest.digest());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            LOGGER.log(Level.WARN, "CANT ENCRYPT", e);
        }
        System.out.println(sha1);
        return sha1;
    }
}