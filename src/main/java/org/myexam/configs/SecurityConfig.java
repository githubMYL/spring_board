package org.myexam.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration  // 설정클래스로 인식
public class SecurityConfig {

    /** 시큐리티 무력화 작업 S */
    @Bean   // 시큐리티 무력화 작업
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.build();
    }

    @Bean   // 시큐리티 무력화 작업
    public WebSecurityCustomizer webSecurityCustomizer() {
        return w -> w.ignoring().requestMatchers("/css/**", "/js/**", "/images/**", "/errors/**");
    }
    /** 시큐리티 무력화 작업 E */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
