package com.hackday.securekeyboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KeypadDto {
    private String encryptedValue;
    private String hashAndEncryptedValue;
    private String b64String;
}
