package com.photograph.backend.member.repository

import com.photograph.backend.member.domain.Member

interface MemberRepository {
    fun save(member: Member): Member
    fun findByEmail(email: String): Member?
    fun findAll(): List<Member>
}

