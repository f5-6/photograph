package com.photograph.backend.config.security

import com.photograph.backend.config.security.handler.AuthenticationSuccessHandler
import com.photograph.backend.config.security.handler.OAuth2UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain

@Configuration
class SecurityConfig(
    private val oAuth2UserHandler: OAuth2UserHandler,
    private val authenticationSuccessHandler: AuthenticationSuccessHandler
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): DefaultSecurityFilterChain =
        http.csrf { it.disable() } // CSRF 보호 비활성화
            .formLogin { it.disable() } // 폼 로그인 비활성화
            .httpBasic { it.disable() } // HTTP 기본 인증 비활성화
            .oauth2Login { oauth2Login ->
                oauth2Login
                    .successHandler(authenticationSuccessHandler) // 성공 핸들러
                    .userInfoEndpoint { it.userService(oAuth2UserHandler) }// 사용자 정보를 가져오는 서비스
            }
            .build()
}
