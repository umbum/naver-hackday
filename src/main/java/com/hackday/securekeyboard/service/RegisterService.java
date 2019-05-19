package com.hackday.securekeyboard.service;

import com.hackday.securekeyboard.dto.*;

public interface RegisterService {
    ReqRegisterToCompDto compareHashing(ReqRegisterCardDto reqRegisterCardDto);
}