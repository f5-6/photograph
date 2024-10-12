package com.photograph.backend.member.service

import com.photograph.backend.member.domain.Member
import com.photograph.backend.member.repository.MongoMemberRepository
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val mongoMemberRepository: MongoMemberRepository,
) {
    /**
     * @param oAuth2User sub, name, given_name, family_name, picture, email, email_verified
     */
    fun merge(oAuth2User: OAuth2User): Member {
        val member = Member(
            username = oAuth2User.attributes["name"] as String,
            email = oAuth2User.attributes["email"] as String,
        )

        return mongoMemberRepository.save(member)
    }
}
