package com.photograph.backend.config.security.component

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder

@Component
class AuthenticationSuccessHandler(
    @Value("\${frontend.url}") private val frontendUrl: String
) : SimpleUrlAuthenticationSuccessHandler() {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val state = request.getParameter("state")

        val redirectUrl =
            if (state != null && state.contains("redirectUrl"))
                UriComponentsBuilder.fromUriString(state)
                    .build().queryParams.getFirst("redirectUrl")
            else ""

        redirectStrategy.sendRedirect(
            request,
            response,
            frontendUrl + redirectUrl
        )
    }
}
