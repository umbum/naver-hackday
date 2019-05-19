package com.hackday.securekeyboard.exception;

import java.time.LocalDateTime;

public class NotFoundUser extends BaseException {
    public NotFoundUser() { this ("User Not Found"); }

    public NotFoundUser(String msg) { this (204, msg); }

    public NotFoundUser(int code, String msg) {
        super(ErrorModel.builder()
                .code(code)
                .msg(msg)
                .timestamp(LocalDateTime.now())
                .build());
    }
}
