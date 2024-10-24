package com.photograph.backend.member.domain

import org.springframework.security.oauth2.core.user.OAuth2User

data class Member(
    val id: String? = null,
    val name: String,
    val email: String,
    val provider: String,
    val providerKey: String,
) {
    companion object {
        /**
         * @param oAuth2User sub, name, given_name, family_name, picture, email, email_verified
         */
        fun create(oAuth2User: OAuth2User, provider: String) = Member(
            name = oAuth2User.attributes["name"] as String,
            email = oAuth2User.attributes["email"] as String,
            provider = provider,
            providerKey = oAuth2User.name
        )
    }
}
