package com.photograph.backend.config.security

import com.photograph.backend.config.security.service.OauthUserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain

@Configuration
class SecurityConfig(
    private val oauthUserService: OauthUserService
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): DefaultSecurityFilterChain =
        http.csrf { it.disable() } // CSRF 보호 비활성화
            .oauth2Login { oauth2Login ->
                oauth2Login
                    .userInfoEndpoint {
                        it.userService(oauthUserService) // 사용자 정보를 가져오는 서비스
                    }
            }
            .build()
}
