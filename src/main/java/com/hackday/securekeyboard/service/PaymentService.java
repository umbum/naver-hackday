package com.hackday.securekeyboard.service;

import com.hackday.securekeyboard.dto.ReqApprovalToCompDto;
import com.hackday.securekeyboard.dto.ReqPaymentDto;
import com.hackday.securekeyboard.vo.CardCompanyInfo;

public interface PaymentService {
    ReqApprovalToCompDto preApproval(CardCompanyInfo cardCompanyInfo, ReqPaymentDto reqPaymentDto);
}
