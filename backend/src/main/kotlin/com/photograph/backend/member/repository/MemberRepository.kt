package com.photograph.backend.member.repository

import com.photograph.backend.member.domain.Member

interface MemberRepository {
    fun save(member: Member): Member
}

