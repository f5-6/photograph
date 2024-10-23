package com.photograph.backend.config.security

import com.photograph.backend.config.security.component.AuthenticationSuccessHandler
import com.photograph.backend.config.security.component.OAuth2UserHandler
import com.photograph.backend.config.security.component.OauthAuthenticationEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain

@Configuration
class SecurityConfig(
    private val oAuth2UserHandler: OAuth2UserHandler,
    private val authenticationSuccessHandler: AuthenticationSuccessHandler,
    private val corsAllowConfiguration: CorsConfig,
    private val oauthAuthenticationEntryPoint: OauthAuthenticationEntryPoint,
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): DefaultSecurityFilterChain =
        http.csrf { it.disable() } // CSRF 보호 비활성화
            .httpBasic { it.disable() } // HTTP 기본 인증 비활성화
            .cors { it.configurationSource(corsAllowConfiguration) } // CORS 설정
            .formLogin { it.disable() }
            .oauth2Login { oauth2Login ->
                oauth2Login
                    .successHandler(authenticationSuccessHandler) // 성공 핸들러
                    .userInfoEndpoint { it.userService(oAuth2UserHandler) } // 사용자 정보를 가져오는 서비스
            }
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/api/**").permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            }
            .exceptionHandling { it.authenticationEntryPoint(oauthAuthenticationEntryPoint) }
            .build()
}
