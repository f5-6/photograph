package com.photograph.backend.photograph.domain

import com.photograph.backend.photograph.infra.PhotographRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

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

    fun getById(id: String): Photograph {
        return photographRepository.findById(id).getOrElse { throw PhotographNotFoundException() }
            .run { PhotographMapper.toDomain(this) }
    }

    fun remove(id: String) = photographRepository.deleteById(id)
}
