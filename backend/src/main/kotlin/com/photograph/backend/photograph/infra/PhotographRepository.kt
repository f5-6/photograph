package com.photograph.backend.photograph.infra

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PhotographRepository : MongoRepository<PhotographDocument, String> {
    fun findByMemberId(memberId: String): List<PhotographDocument>
}
