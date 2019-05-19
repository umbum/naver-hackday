package com.hackday.securekeyboard.dao;

import com.hackday.securekeyboard.vo.Key;

public interface KeyDao {
    Key getPublicKey(int cardCompany);
}
