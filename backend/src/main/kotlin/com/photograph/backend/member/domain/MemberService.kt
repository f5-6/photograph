package com.photograph.backend.member.domain

import com.photograph.backend.member.infra.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(private val memberRepository: MemberRepository) {
    fun merge(member: Member): Member {
        return findByName(member.name)?.let { return it }
            ?: memberRepository.save(MemberMapper.toEntity(member))
                .run { MemberMapper.toDomain(this) }
    }

    fun findByName(name: String): Member? {
        return memberRepository.findByName(name)?.let {
            MemberMapper.toDomain(it)
        }
    }
}
