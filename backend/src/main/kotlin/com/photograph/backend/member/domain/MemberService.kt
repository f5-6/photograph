package com.photograph.backend.member.domain

import com.photograph.backend.member.infra.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(private val memberRepository: MemberRepository) {
    fun merge(member: Member): Member {
        return findByProviderKey(member.provider, member.providerKey) ?: save(member)
    }

    private fun findByProviderKey(provider: String, providerKey: String): Member? {
        return memberRepository.findByProviderAndProviderKey(provider, providerKey)
            ?.let { MemberMapper.toDomain(it) }
    }

    private fun save(member: Member): Member {
        return memberRepository.save(MemberMapper.toEntity(member))
            .run { MemberMapper.toDomain(this) }
    }
}
