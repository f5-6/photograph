package com.photograph.backend.member.repository

import com.photograph.backend.member.domain.Member
import org.springframework.stereotype.Repository

@Repository
class InMemoryMemberRepository : MemberRepository {
    private val store: MutableMap<String, Member> = mutableMapOf()

    override fun save(member: Member): Member {
        store[member.email] = member
        return member
    }

    override fun findByEmail(email: String): Member? {
        return store[email]
    }

    override fun findAll(): List<Member> {
        return store.values.toList()
    }
}
