package com.hackday.securekeyboard.exception;

import java.time.LocalDateTime;

public class WrongPassword extends BaseException {
    public WrongPassword() { this ("잘못된 패스워드입니다."); }

    public WrongPassword(String msg) { this (204, msg); }

    public WrongPassword(int code, String msg) {
        super(ErrorModel.builder()
                .code(code)
                .msg(msg)
                .timestamp(LocalDateTime.now())
                .build());
    }
}
