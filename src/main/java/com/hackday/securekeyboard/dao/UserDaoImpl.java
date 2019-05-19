package com.hackday.securekeyboard.dao;

import com.hackday.securekeyboard.dto.ResRegisterResultDto;
import com.hackday.securekeyboard.vo.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{
    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean isExistedUser(int userId){
        String checkUserQuery = "SELECT EXISTS (SELECT * FROM users WHERE user_id = ?) AS success";
        int result = jdbcTemplate.queryForObject(checkUserQuery, new Object[]{userId}, Integer.class);

        return result == 1;
    }

    @Override
    public boolean checkPassword(int userId, String payPw) {
        User user = selectUser(userId);
        String  password = user.getPayPw();

        return payPw.equals(password);
    }

    @Override
    public int updateToken(int userId, String token) {
        String updateTokenQuery = "UPDATE users SET token = ? WHERE user_id = ?";
        return jdbcTemplate.update(updateTokenQuery, token, userId);
    }

    @Override
    public String getToken(int userId) {
        User user = selectUser(userId);
        return user.getToken();
    }

    private User selectUser(int userId) {
        String selectPayPwQuery = "SELECT * FROM users WHERE user_id = ?";
        User user = jdbcTemplate.queryForObject(selectPayPwQuery, new Object[]{userId}, new BeanPropertyRowMapper<>(User.class));
        return user;
    }
}
