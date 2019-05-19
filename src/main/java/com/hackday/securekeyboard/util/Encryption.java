package com.hackday.securekeyboard.util;

import com.hackday.securekeyboard.exception.EncryptException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Slf4j
@Component
public class Encryption {
    private static final String[] NUMBERS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    @Value(value = "${publicKeyA}")
    private String publicKey;


    public List<String> rsaEncryption() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException {
        List<String> rsaNumbers = new ArrayList<String>();

        log.debug("{}", publicKey);

        Key decodedKey = KeyFactory.getInstance("RSA").generatePublic(
            new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey)));


        Cipher cipher = Cipher.getInstance("RSA");

//        @TODO : Exception
        try{
            for (String number : NUMBERS) {
                cipher.init(Cipher.ENCRYPT_MODE, decodedKey);
                byte[] ciphrerArr = cipher.doFinal(number.getBytes());
                String cipherNum = Base64.getEncoder().encodeToString(ciphrerArr);
                rsaNumbers.add(cipherNum);
            }
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            throw new EncryptException();
        }

        return rsaNumbers;
    }

    public List<String> rsaToSha1(List<String> rsaNumbers) {
        List<String> sha1Number = sha1(rsaNumbers);
        return sha1Number;
    }

    private List<String> sha1(List<String> object){
        List<String> hashArray = new ArrayList<>();

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            for(String hashNumber : object){
                digest.update(hashNumber.getBytes("utf8"));
                hashArray.add(String.format("%040x", new BigInteger(1, digest.digest())));
            }
        } catch (Exception e){
//            @TODO : Exception
            e.printStackTrace();
        }

        return hashArray;
    }

}
