package com.photograph.backend.member.domain

import com.photograph.backend.member.infra.MemberDocument

object MemberMapper {
    fun toDomain(memberDocument: MemberDocument) = with(memberDocument) {
        Member(
            id = memberId,
            name = name,
            email = email,
            provider = provider,
            providerKey = providerKey
        )
    }

    fun toEntity(member: Member) = with(member) {
        MemberDocument(
            name = name,
            email = email,
            provider = provider,
            providerKey = providerKey
        )
    }
}
