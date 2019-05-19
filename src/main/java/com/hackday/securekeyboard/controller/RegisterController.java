package com.hackday.securekeyboard.controller;

import com.hackday.securekeyboard.dto.ReqRegisterCardDto;
import com.hackday.securekeyboard.dto.ReqRegisterToCompDto;
import com.hackday.securekeyboard.dto.ResRegisterResultDto;
import com.hackday.securekeyboard.service.MediationService;
import com.hackday.securekeyboard.service.RegisterService;
import com.hackday.securekeyboard.vo.CardCompanyInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;

@RestController
@RequestMapping(value = "/register")
public class RegisterController {
    private final RegisterService registerService;
    private final MediationService mediationService;

    public RegisterController(RegisterService registerService, MediationService mediationService) {
        this.registerService = registerService;
        this.mediationService = mediationService;
    }

    @PostMapping("/card/{card-company}")
    public ResponseEntity registerCard(@PathVariable("card-company") String cardCompany,
                                       @RequestBody ReqRegisterCardDto reqRegisterCardDto){

        CardCompanyInfo cardCompanyInfo = CardCompanyInfo.valueOfName(cardCompany);

        ReqRegisterToCompDto reqRegisterToCompDto = registerService.compareHashing(reqRegisterCardDto);
        ResRegisterResultDto resRegisterResultDto = mediationService.registerCard(cardCompanyInfo, reqRegisterToCompDto);

//        @TODO : client response
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resRegisterResultDto);
    }
}
