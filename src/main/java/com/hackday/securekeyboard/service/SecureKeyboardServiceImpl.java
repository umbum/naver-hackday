package com.hackday.securekeyboard.service;

import static com.hackday.securekeyboard.SecureKeyboardApplication.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.hackday.securekeyboard.dto.KeypadDto;
import com.hackday.securekeyboard.util.Encryption;
import com.hackday.securekeyboard.vo.KeyMappingSet;

@Service
public class SecureKeyboardServiceImpl implements SecureKeyboardService {

    @Autowired
    private Encryption encryptionUtil;

    private String getBase64FromFile(String classPathResource) {
        byte[] fileContent = null;
        try {
            File keyImage = new ClassPathResource(classPathResource).getFile();
            fileContent = FileUtils.readFileToByteArray(keyImage);
        } catch (IOException e) {
            // TODO : runtime exception 상속 받아서 던질 것.
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(fileContent);
    }

    @Override
    public ArrayList<KeypadDto> generateKeypadImages() {
        ArrayList<KeypadDto> keypadDtoList = new ArrayList<KeypadDto>(10);
        List<String> encryptedKeyList = null;
        List<String> hashedAndEncryptedKeyList = null;
        try {
            encryptedKeyList = encryptionUtil.rsaEncryption();
            hashedAndEncryptedKeyList = encryptionUtil.rsaToSha1(encryptedKeyList);
        } catch (Exception e) {
            // TODO : runtime exception 상속받아서 던질 것!
            e.printStackTrace();
        }

        // 이미지 불러와서 base64 적용하고,
        ArrayList<String> b64ImageStrings = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            b64ImageStrings.add(getBase64FromFile("img/" + i + ".png"));
            keypadDtoList.add(new KeypadDto(
                encryptedKeyList.get(i),
                hashedAndEncryptedKeyList.get(i),
                b64ImageStrings.get(i)
            ));
        }

        keypadDtoList.add(new KeypadDto("blank", "blank", getBase64FromFile("img/blank.png")));
        keypadDtoList.add(new KeypadDto("blank", "blank", getBase64FromFile("img/blank.png")));

        // 여기서 순서를 섞어 준 다음에
        Collections.shuffle(keypadDtoList);
        return keypadDtoList;
    }

    @Override
    public String addToKeyMappingTable(ArrayList<KeypadDto> keypadDtoList) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        KeyMappingSet keyMappingSet = new KeyMappingSet();
        keyMappingSet.setEncrytedOnly(new ArrayList<>(10));
        keyMappingSet.setHashedAndEncrypted(new ArrayList<>(10));

        for (KeypadDto keypadDto : keypadDtoList) {
            keyMappingSet.getEncrytedOnly().add(keypadDto.getEncryptedValue());
            keyMappingSet.getHashedAndEncrypted().add(keypadDto.getHashAndEncryptedValue());
        }

        globalKeyMappingTable.put(uuid, keyMappingSet);
        return uuid;
    }

}
