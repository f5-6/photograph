package com.photograph.backend.config.security.component

import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest
import org.springframework.web.util.UriComponentsBuilder

class AuthorizationRequestHandler(
    clientRegistrationRepository: ClientRegistrationRepository,
    authorizationRequestBaseUri: String = "/oauth2/authorization"
) : OAuth2AuthorizationRequestResolver {

    private val defaultResolver =
        DefaultOAuth2AuthorizationRequestResolver(clientRegistrationRepository, authorizationRequestBaseUri)

    override fun resolve(request: HttpServletRequest): OAuth2AuthorizationRequest? {
        val authorizationRequest: OAuth2AuthorizationRequest = defaultResolver.resolve(request) ?: return null

        return customizeAuthorizationRequest(request, authorizationRequest)
    }

    override fun resolve(request: HttpServletRequest, clientRegistrationId: String): OAuth2AuthorizationRequest? {
        val authorizationRequest: OAuth2AuthorizationRequest =
            defaultResolver.resolve(request, clientRegistrationId) ?: return null

        return customizeAuthorizationRequest(request, authorizationRequest)
    }

    private fun customizeAuthorizationRequest(
        request: HttpServletRequest,
        authorizationRequest: OAuth2AuthorizationRequest
    ): OAuth2AuthorizationRequest {
        // 쿼리 파라미터에서 redirectUrl을 추출하고, state 파라미터로 포함
        val redirectUrl = request.getParameter("redirectUrl")
        val builder = OAuth2AuthorizationRequest.from(authorizationRequest)

        if (redirectUrl != null) {
            val state = UriComponentsBuilder
                .fromUriString(authorizationRequest.state)
                .queryParam("redirectUrl", redirectUrl)
                .build().toUriString()

            builder.state(state)
        }

        return builder.build()
    }
}
