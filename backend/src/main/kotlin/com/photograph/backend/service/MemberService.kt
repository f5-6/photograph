package com.photograph.backend.service

import com.photograph.backend.domain.Member
import com.photograph.backend.repository.MemberRepository
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {
    fun merge(oAuth2User: OAuth2User): Member {
        val member = Member(
            username = oAuth2User.attributes["name"] as String,
            email = oAuth2User.attributes["email"] as String,
            picture = oAuth2User.attributes["picture"] as String,
            registerDate = LocalDateTime.now()
        )
        return memberRepository.save(member)
    }

    fun findAll(): List<Member> {
        return memberRepository.findAll()
    }
}