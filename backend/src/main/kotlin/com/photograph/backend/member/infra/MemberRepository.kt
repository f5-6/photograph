package com.photograph.backend.member.infra

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : MongoRepository<MemberDocument, String> {
    fun findByProviderAndProviderKey(provider: String, providerKey: String): MemberDocument?
}
