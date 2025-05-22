package com.example.scheduledevelop.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 * 비밀번호를 암호화 해줍니다.
 */
@Component
public class PasswordEncoder {

    //입력받은 비밀번호를 암호화해서 반환해줍니다.
    public String encode(String rawPassword) {
        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
    }

    //입력받은 비밀번호와 암호화된 비밀번호가 같은지 확인하고 일치하면 false 다르면 true를 반환합니다.
    public boolean matches(String rawPassword, String encodedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
        return !result.verified;
    }
}