package com.photograph.backend.photograph.domain

import com.photograph.backend.photograph.application.dto.PostPhotographDTO
import java.time.LocalDate
import java.time.LocalDateTime

data class Photograph(
    val id: String? = null,
    val memberId: String,
    val url: String,
    val description: String,
    val tookAt: LocalDate,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?,
) {
    companion object {
        fun create(dto: PostPhotographDTO, url: String): Photograph {
            return with(dto) {
                Photograph(
                    memberId = memberId,
                    url = url,
                    description = description,
                    tookAt = tookAt,
                    createdAt = LocalDateTime.now(),
                    updatedAt = null,
                )
            }
        }
    }
}
