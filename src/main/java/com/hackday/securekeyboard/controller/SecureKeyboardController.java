package com.hackday.securekeyboard.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hackday.securekeyboard.dto.KeypadDto;
import com.hackday.securekeyboard.service.SecureKeyboardService;

@Controller
@RequestMapping(value = "/securekeyboard")
public class SecureKeyboardController {
    private final SecureKeyboardService secureKeyboardService;

    public SecureKeyboardController(SecureKeyboardService secureKeyboardService) {
        this.secureKeyboardService = secureKeyboardService;
    }

    @GetMapping("/{card-company}")
    public String getSecureKeyboard(@PathVariable("card-company") String cardCompany,
                                    Model model) {

        ArrayList<KeypadDto> keypadDtos = secureKeyboardService.generateKeypadImages();
        String uuid = secureKeyboardService.addToKeyMappingTable(keypadDtos);
        model.addAttribute("keyMapId", uuid);
        model.addAttribute("keypadDtoList", keypadDtos);

        return "secure-keyboard";
    }
}
