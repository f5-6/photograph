package com.photograph.backend.member.domain

import com.photograph.backend.member.infra.MemberDocument
import org.springframework.security.oauth2.core.user.OAuth2User

object MemberMapper {
    fun toDomain(memberDocument: MemberDocument) = with(memberDocument) {
        Member(
            name = name,
            email = email,
            provider = provider,
            providerKey = providerKey
        )
    }

    /**
     * @param oAuth2User sub, name, given_name, family_name, picture, email, email_verified
     */
    fun toDomain(oAuth2User: OAuth2User, provider: String) = Member(
        name = oAuth2User.attributes["name"] as String,
        email = oAuth2User.attributes["email"] as String,
        provider = provider,
        providerKey = oAuth2User.name
    )

    fun toEntity(member: Member) = with(member) {
        MemberDocument(
            name = name,
            email = email,
            provider = provider,
            providerKey = providerKey
        )
    }
}
