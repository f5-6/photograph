package com.photograph.backend.domain

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class MemberAuthority(
    val memberAuthorityId: String = ObjectId().toString(),
    val memberId: String,
    val authority: Role,
    val deleted: Boolean = false
)

enum class Role {
    ROLE_USER,
    ROLE_ADMIN
}