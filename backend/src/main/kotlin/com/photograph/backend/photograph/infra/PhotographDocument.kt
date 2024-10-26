package com.photograph.backend.photograph.infra

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalDateTime

@Document(value = "photograph")
data class PhotographDocument(
    @Id
    val id: String = ObjectId().toString(),
    val memberId: String,
    val url: String,
    val description: String,
    val tookAt: LocalDate,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime? = null,
)
