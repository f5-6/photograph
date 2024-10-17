package com.photograph.backend.member.infra

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(value = "member")
data class MemberDocument(
    @Id
    val memberId: String = ObjectId().toString(),
    val name: String,
    val email: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val provider: String,
    val providerKey: String,
)
