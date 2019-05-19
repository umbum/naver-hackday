package com.hackday.securekeyboard.service;

import java.util.ArrayList;

import com.hackday.securekeyboard.dto.KeypadDto;

public interface SecureKeyboardService {
    public ArrayList<KeypadDto> generateKeypadImages();
    public String addToKeyMappingTable(ArrayList<KeypadDto> keypadDtoList);
}
