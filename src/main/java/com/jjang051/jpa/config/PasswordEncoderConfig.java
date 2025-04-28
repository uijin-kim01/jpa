package com.jjang051.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoderConfig {

    // container에 등록된다
    // BCryptPasswordEncoder는 암호화 후 풀이가 불가능.
    // salt로 만들어 뿌린다. 1234를 쓰더라도 각기 다른 암호가 생성됨

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
