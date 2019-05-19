package com.hackday.securekeyboard.controller;

import com.hackday.securekeyboard.dto.ReqApprovalToCompDto;
import com.hackday.securekeyboard.dto.ReqPaymentDto;
import com.hackday.securekeyboard.dto.ResApprovalResultDto;
import com.hackday.securekeyboard.service.MediationService;
import com.hackday.securekeyboard.service.PaymentService;
import com.hackday.securekeyboard.vo.CardCompanyInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;
    private final MediationService mediationService;

    public PaymentController(PaymentService paymentService, MediationService mediationService) {
        this.paymentService = paymentService;
        this.mediationService = mediationService;
    }

    @GetMapping("/{card-company}")
    public String getSecureKeyboard(@PathVariable("card-company") String cardCompany){
        CardCompanyInfo cardCompanyInfo = CardCompanyInfo.valueOfName(cardCompany);
        String companyName = cardCompanyInfo.getName();
        return "secure-keyboard.html";
//        String img = "../static/images/" + companyName + ".png";
//        return img;
    }

    @PostMapping("/check/{card-company}")
    public ResponseEntity checkPayPassword(@PathVariable("card-company") String cardCompany,
                                           @RequestBody ReqPaymentDto reqPaymentDto){
        CardCompanyInfo cardCompanyInfo = CardCompanyInfo.valueOfName(cardCompany);

        ReqApprovalToCompDto reqApprovalToCompDto = paymentService.preApproval(cardCompanyInfo, reqPaymentDto);
        ResApprovalResultDto resApprovalResultDto = mediationService.approvalPayment(cardCompanyInfo, reqApprovalToCompDto);

//        @TODO : client response
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
