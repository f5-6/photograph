package com.photograph.backend.member.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User
import java.time.LocalDateTime

@Document
data class Member(
    @Id
    val memberId: String = ObjectId().toString(),
    val username: String,
    val email: String,
    val registerDate: LocalDateTime = LocalDateTime.now(),
    val deleted: Boolean = false
) : OAuth2User {
    override fun getName(): String {
        return username
    }

    override fun getAttributes(): MutableMap<String, Any> {
        return mutableMapOf(
            "memberId" to memberId,
            "username" to username,
            "email" to email,
            "registerDate" to registerDate,
            "deleted" to deleted
        )
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(GrantedAuthority { "ROLE_ADMIN" })
    }
}