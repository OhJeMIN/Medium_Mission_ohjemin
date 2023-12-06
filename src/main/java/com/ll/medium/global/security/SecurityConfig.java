package com.ll.medium.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 이 클래스는 스프링 설정 클래스임을 나타냅니다.
public class SecurityConfig {

    // SecurityFilterChain 타입을 반환하는 메서드입니다. HttpSecurity를 인자로 받습니다.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequest -> // 인증 요청에 대한 설정을 합니다.
                        authorizeRequest.requestMatchers("/**") // 모든 요청을 대상으로 합니다.
                                .permitAll() // 모든 요청을 허용합니다.
                )
                .headers(
                        headers -> // response headers에 대한 설정을 합니다.
                                headers.frameOptions( // frame 옵션에 대한 설정을 합니다.
                                        frameOptions ->
                                                frameOptions.sameOrigin() // 같은 출처의 페이지만 iframe 내에서 표시하도록 설정합니다.
                                )
                )
                .csrf( // CSRF 보호에 대한 설정을 합니다.
                        csrf ->
                                csrf.ignoringRequestMatchers( // CSRF 보호를 무시할 요청 매칭 패턴을 설정합니다.
                                        "/h2-console/**" // "/h2/console/**" 패턴의 요청은 CSRF 보호를 무시합니다.
                                )
                );

        return http.build(); // 위의 설정에 따라 HttpSecurity 객체를 빌드하고, SecurityFilterChain 객체를 반환합니다.
    }
}
