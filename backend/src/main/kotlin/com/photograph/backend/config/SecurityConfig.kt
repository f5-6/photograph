package com.photograph.backend.config

import com.photograph.backend.service.OauthUserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    private val oauthUserService: OauthUserService
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() } // CSRF 보호 비활성화
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/", "/login/**").permitAll() // 메인 페이지와 로그인 경로는 모두 접근 가능
                    .anyRequest().authenticated() // 그 외의 요청은 인증 필요
            }
            .oauth2Login { oauth2Login ->
                oauth2Login
                    .defaultSuccessUrl("/") // 로그인 성공 시 리다이렉트될 URL
                    .failureUrl("/login?error=true") // 로그인 실패 시 리다이렉트될 URL
                    .userInfoEndpoint {
                        it.userService(oauthUserService) // 사용자 정보를 가져오는 서비스
                    }
            }

        return http.build()
    }
}
