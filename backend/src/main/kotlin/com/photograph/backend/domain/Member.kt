package com.photograph.backend.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class Member(
    @Id
    val memberId: String = ObjectId().toString(),
    val username: String,
    val email: String,
    val picture: String,
    val registerDate: LocalDateTime = LocalDateTime.now(),
    val deleted: Boolean = false
) {
}