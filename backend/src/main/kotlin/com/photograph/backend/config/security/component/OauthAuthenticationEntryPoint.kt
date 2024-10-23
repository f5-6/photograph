package com.photograph.backend.config.security.component

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class OauthAuthenticationEntryPoint : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        val errorMessage = authException.message ?: "Unauthorized"
        response.apply {
            contentType = APPLICATION_JSON_VALUE
            status = HttpServletResponse.SC_UNAUTHORIZED
            writer.write("{\"message\": \"$errorMessage\"}")
        }
    }
}
