package com.photograph.backend.member.service

import com.photograph.backend.member.domain.Member
import com.photograph.backend.member.repository.MemberRepository
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {
    fun merge(oAuth2User: OAuth2User): Member {
        //todo : 처음 가입한지 확인 후 권한 부여 로직 추가
        val member = Member(
            username = oAuth2User.attributes["name"] as String,
            email = oAuth2User.attributes["email"] as String,
            registerDate = LocalDateTime.now()
        )
        return memberRepository.save(member)
    }
}