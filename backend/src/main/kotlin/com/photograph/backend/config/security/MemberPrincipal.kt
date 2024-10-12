package com.photograph.backend.config.security

import com.photograph.backend.member.domain.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User

data class MemberPrincipal(
    private val member: Member
) : OAuth2User {
    override fun getName(): String = member.name

    override fun getAttributes(): MutableMap<String, Any> = with(member) {
        mutableMapOf(
            "username" to name,
            "email" to email,
        )
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        mutableListOf(GrantedAuthority { "ROLE_ADMIN" })
}
