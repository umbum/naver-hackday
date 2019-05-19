package com.hackday.securekeyboard.service;

import com.hackday.securekeyboard.dto.ReqApprovalToCompDto;
import com.hackday.securekeyboard.dto.ReqRegisterToCompDto;
import com.hackday.securekeyboard.dto.ResApprovalResultDto;
import com.hackday.securekeyboard.dto.ResRegisterResultDto;
import com.hackday.securekeyboard.vo.CardCompanyInfo;

public interface MediationService {
    ResRegisterResultDto registerCard(CardCompanyInfo cardCompanyInfo, ReqRegisterToCompDto reqRegisterToCompDto);
    ResApprovalResultDto approvalPayment(CardCompanyInfo cardCompanyInfo, ReqApprovalToCompDto reqApprovalToCompDto);
}
