package com.hackday.securekeyboard;

import com.hackday.securekeyboard.vo.KeyMappingSet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Hashtable;

@SpringBootApplication
public class SecureKeyboardApplication {
    public static Hashtable<String, KeyMappingSet> globalKeyMappingTable;

    public static void main(String[] args) {
        globalKeyMappingTable = new Hashtable<>();
        SpringApplication.run(SecureKeyboardApplication.class, args);
    }
}