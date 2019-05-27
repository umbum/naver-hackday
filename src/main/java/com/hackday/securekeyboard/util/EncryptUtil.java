package com.hackday.securekeyboard.util;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : 카드결제개발 / 주윤덕 (yoondeok.ju@navercorp.com)
 * @since : 2019-05-21
 */
@Slf4j
public class EncryptUtil {
    /**
     * sha1 hashing
     *
     * @param text
     * @return
     */
    public static String sha1(String text) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(text.getBytes("utf8"));
        } catch (Exception e) {
            log.error("### failed to sha1. {}", text);
            throw new IllegalStateException(e);
        }

        return String.format("%040x", new BigInteger(1, digest.digest()));
    }

    /**
     * encrypt RSA
     *
     * @param text
     * @param publicKey
     * @return
     */
    public static String rsa(String text, String publicKey) {
        byte[] encrypted;
        try {
            Key decodedKey = KeyFactory.getInstance("RSA").generatePublic(
                new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey)));

            Cipher cipher = Cipher.getInstance("RSA");

            cipher.init(Cipher.ENCRYPT_MODE, decodedKey);
            encrypted = cipher.doFinal(text.getBytes());
        } catch (Exception e) {
            log.error("### failed to rsa encrypt. {}", text);
            throw new IllegalStateException(e);
        }

        return Base64.getEncoder().encodeToString(encrypted);
    }
}
