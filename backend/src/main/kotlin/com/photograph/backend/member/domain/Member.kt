package com.photograph.backend.member.domain

data class Member(
    val name: String,
    val email: String,
    val provider: String,
    val providerKey: String,
)
