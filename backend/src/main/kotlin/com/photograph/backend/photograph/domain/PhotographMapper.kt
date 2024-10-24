package com.photograph.backend.photograph.domain

import com.photograph.backend.photograph.infra.PhotographDocument

object PhotographMapper {
    fun toDomain(photographDocument: PhotographDocument) = with(photographDocument) {
        Photograph(
            id = id,
            memberId = memberId,
            url = url,
            description = description,
            tookAt = tookAt,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    fun toEntity(photograph: Photograph) = with(photograph) {
        PhotographDocument(
            id = id,
            memberId = memberId,
            url = url,
            description = description,
            tookAt = tookAt,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
}
