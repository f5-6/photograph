package com.photograph.backend.photograph.domain

import com.photograph.backend.photograph.infra.PhotographRepository
import org.springframework.stereotype.Service

@Service
class PhotographService(private val photographRepository: PhotographRepository) {
    fun save(photograph: Photograph): Photograph {
        return photographRepository.save(
            PhotographMapper.toEntity(photograph)
        ).run { PhotographMapper.toDomain(this) }
    }

    fun get(memberId: String): List<Photograph> {
        return photographRepository.findByMemberId(memberId).map {
            PhotographMapper.toDomain(it)
        }
    }
}
