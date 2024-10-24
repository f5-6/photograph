package com.photograph.backend.photograph.domain

import java.time.LocalDate
import java.time.LocalDateTime

data class Photograph(
    val id: String,
    val memberId: String,
    val url: String,
    val description: String,
    val tookAt: LocalDate,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?,
)
