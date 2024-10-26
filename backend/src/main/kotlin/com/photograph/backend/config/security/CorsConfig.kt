package com.photograph.backend.config.security

import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource

@Component
class CorsConfig(
    @Value("\${frontend.url}") private val frontendUrl: String
) : CorsConfigurationSource {
    override fun getCorsConfiguration(request: HttpServletRequest): CorsConfiguration {
        return CorsConfiguration().apply {
            allowedOriginPatterns = listOf("http://localhost:*", "")
            allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "PATCH")
            allowedHeaders = listOf("*")
            allowCredentials = true
        }
    }
}
