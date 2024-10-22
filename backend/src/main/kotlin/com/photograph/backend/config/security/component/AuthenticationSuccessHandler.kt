package com.photograph.backend.config.security.component

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class AuthenticationSuccessHandler(
    @Value("\${frontend.url}") private val frontendUrl: String
) : SimpleUrlAuthenticationSuccessHandler() {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        response?.addCookie(
            Cookie("JSESSIONID", request?.session?.id).apply {
                maxAge = 60 * 5
            }
        )
        redirectStrategy.sendRedirect(
            request,
            response,
            frontendUrl
        )
    }
}
