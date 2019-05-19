package com.hackday.securekeyboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ResRegisterResultDto {
    private boolean status;
    private String cardNo;
}
