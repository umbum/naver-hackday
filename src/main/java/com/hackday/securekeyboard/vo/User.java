package com.hackday.securekeyboard.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int userId;
    private String payPw;
    private String token;

    public User(int userId, String token) {
        userId = this.userId;
        token = this.token;
    }
}
