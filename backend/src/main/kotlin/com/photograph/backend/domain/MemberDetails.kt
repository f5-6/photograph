package com.photograph.backend.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User

data class MemberDetails(
    val member: Member,
    val role: List<String>,
    val authAttributes: MutableMap<String, Any>
) : OAuth2User {

    override fun getName(): String = member.username

    override fun getAttributes(): MutableMap<String, Any> = authAttributes

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        role.map { GrantedAuthority { it } }.toMutableList()

}