package com.hackday.securekeyboard.vo;

import lombok.Data;

import java.util.ArrayList;

@Data
public class KeyMappingSet {
    private ArrayList<String> hashedAndEncrypted;
    private ArrayList<String> encrytedOnly;
}
