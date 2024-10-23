package com.photograph.backend.config.security.component

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Component

@Component
class Oauth2LogoutHandler : LogoutSuccessHandler {
    override fun onLogoutSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        val cookieClearingLogoutHandler = CookieClearingLogoutHandler("JSESSIONID")
        val securityContextLogoutHandler = SecurityContextLogoutHandler()
        cookieClearingLogoutHandler.logout(request, response, authentication)
        securityContextLogoutHandler.logout(request, response, authentication)
        response?.status = HttpServletResponse.SC_OK
    }
}
