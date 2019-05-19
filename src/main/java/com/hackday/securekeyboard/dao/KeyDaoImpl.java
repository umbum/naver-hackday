package com.hackday.securekeyboard.dao;

import com.hackday.securekeyboard.vo.Key;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class KeyDaoImpl implements KeyDao {

    private final JdbcTemplate jdbcTemplate;

    public KeyDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Key getPublicKey(int cardCompany) {
        String selectKeyQuery = "SELECT public_key FROM key WHERE card_company = ?";

        return jdbcTemplate.queryForObject(selectKeyQuery, new Object[]{cardCompany}, new BeanPropertyRowMapper<>(Key.class));
    }
}
