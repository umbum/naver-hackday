package com.hackday.securekeyboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReqRegisterCardDto {
    private String reqId;
    private List<String> cardNumbers;
}
