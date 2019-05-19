package com.hackday.securekeyboard.service;

import com.hackday.securekeyboard.dto.ReqRegisterCardDto;
import com.hackday.securekeyboard.dto.ReqRegisterToCompDto;
import com.hackday.securekeyboard.vo.KeyMappingSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.hackday.securekeyboard.SecureKeyboardApplication.globalKeyMappingTable;

@Slf4j
@Service
public class RegisterServiceImpl implements RegisterService{

    @Override
    public ReqRegisterToCompDto compareHashing(ReqRegisterCardDto reqRegisterCardDto) {
        ReqRegisterToCompDto reqRegisterToCompDto = new ReqRegisterToCompDto();
        reqRegisterToCompDto.setEncryptedCardNo(compareHash(reqRegisterCardDto.getCardNumbers(), reqRegisterCardDto.getReqId()));

        return reqRegisterToCompDto;
    }

    private List<String> compareHash(List<String> userInput, String reqId){
        List<String> results = new ArrayList<>();

        KeyMappingSet keyMap = globalKeyMappingTable.get(reqId);

        for(String input : userInput){
            for(int i = 0; i < keyMap.getHashedAndEncrypted().size(); i++){
                if(input.equals(keyMap.getHashedAndEncrypted().get(i))){
                    results.add(keyMap.getEncrytedOnly().get(i));
                }
            }
        }
        return results;
    }

}
