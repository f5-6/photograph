package com.photograph.backend.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User
import java.time.LocalDateTime
import java.util.*

data class Member(
    val id: String = UUID.randomUUID().toString(),
    val username: String,
    val email: String,
    val picture: String,
    val registerDate: LocalDateTime,
    val deleted: Boolean = false,
    val role: String = "ROLE_USER",
) : OAuth2User {

    @JsonIgnore
    override fun getName(): String = username

    @JsonIgnore
    override fun getAttributes(): MutableMap<String, Any> = mutableMapOf(
        "name" to name,
        "email" to email,
        "picture" to picture,
    )

    @JsonIgnore
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        mutableListOf(GrantedAuthority { role })

}