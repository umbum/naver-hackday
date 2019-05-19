package com.hackday.securekeyboard.exception;

import java.time.LocalDateTime;

public class EncryptException extends BaseException{
    public EncryptException(){ this("Encryption Error"); }

    public EncryptException(String msg) { this(204, msg); }

    public EncryptException(int code, String msg) {
        super(ErrorModel.builder()
                .code(code)
                .msg(msg)
                .timestamp(LocalDateTime.now())
                .build());
    }


}
