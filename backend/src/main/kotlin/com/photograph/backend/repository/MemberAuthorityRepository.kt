package com.photograph.backend.repository

import com.photograph.backend.domain.MemberAuthority
import org.springframework.stereotype.Service

interface MemberAuthorityRepository {
    fun findMemberRoles(id: String): List<MemberAuthority>
}

@Service
class InMemoryMemberAuthorityRepository : MemberAuthorityRepository {
    private val store: MutableMap<String, MemberAuthority> = mutableMapOf()

    fun save(memberId: String, authority: MemberAuthority) {
        store[memberId] = authority
    }

    override fun findMemberRoles(id: String): List<MemberAuthority> {
        return store[id]?.let { listOf(it) } ?: emptyList()
    }
}