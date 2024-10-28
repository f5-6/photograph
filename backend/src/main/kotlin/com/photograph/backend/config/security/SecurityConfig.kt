package com.photograph.backend.config.security

import com.photograph.backend.config.security.component.AuthenticationSuccessHandler
import com.photograph.backend.config.security.component.AuthorizationRequestHandler
import com.photograph.backend.config.security.component.OAuth2UserHandler
import com.photograph.backend.config.security.component.Oauth2LogoutHandler
import com.photograph.backend.config.security.component.OauthAuthenticationEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.web.DefaultSecurityFilterChain

@Configuration
class SecurityConfig(
    private val corsAllowConfiguration: CorsConfig,
    private val oauth2LogoutHandler: Oauth2LogoutHandler,
    private val oAuth2UserHandler: OAuth2UserHandler,
    private val authenticationSuccessHandler: AuthenticationSuccessHandler,
    private val oauthAuthenticationEntryPoint: OauthAuthenticationEntryPoint,
    private val clientRegistrationRepository: ClientRegistrationRepository
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): DefaultSecurityFilterChain {
        return http.csrf { it.disable() } // CSRF 보호 비활성화
            .httpBasic { it.disable() } // HTTP 기본 인증 비활성화
            .cors { it.configurationSource(corsAllowConfiguration) } // CORS 설정
            .formLogin { it.disable() }.oauth2Login { oauth2Login ->
                oauth2Login.successHandler(authenticationSuccessHandler) // 성공 핸들러
                    .userInfoEndpoint { it.userService(oAuth2UserHandler) } // 사용자 정보를 가져오는 서비스
                    .authorizationEndpoint {
                        it.authorizationRequestResolver(
                            AuthorizationRequestHandler(
                                clientRegistrationRepository
                            )
                        )
                    }
            }
            .logout {
                it.logoutSuccessHandler(oauth2LogoutHandler)
                    .invalidateHttpSession(true)
            }
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/api/**").permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            }
            .exceptionHandling { it.authenticationEntryPoint(oauthAuthenticationEntryPoint) }.build()
    }

}
