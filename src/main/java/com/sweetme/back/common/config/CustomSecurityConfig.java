package com.sweetme.back.common.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@Log4j2
@RequiredArgsConstructor
public class CustomSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        log.info("------------------security config-------------------");

        http.cors(httpSecurityCorsConfigurer ->
                httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource()));

        http.sessionManagement(sessionConfig ->
                sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // 세션 생성 금지 설정(무상태)

        http.csrf(config -> config.disable()); // csrf 토큰 미사용 설정(API 는 세션 쿠키 대신 JWT 를 사용하므로 CSRF 공격의 대상이 아님)

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration(); // CORS 정책 정의 객체

        configuration.setAllowedOriginPatterns(Arrays.asList("*")); // 요청이 허용되는 출처
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE")); // 요청시 사용 가능한 HTTP 메서드
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type")); // 요청시 사용 가능한 헤더
        configuration.setAllowCredentials(true); // 요청에 자격증명 정보 포함 허용 여부

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); // URL 경로별로 CORS 설정을 매핑
        source.registerCorsConfiguration("/**", configuration); // 애플리케이션의 모든 경로에 CORS 설정 적용

        return source;
    }

}
