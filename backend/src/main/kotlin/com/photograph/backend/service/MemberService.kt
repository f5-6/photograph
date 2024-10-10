package com.photograph.backend.service

import com.photograph.backend.domain.Member
import com.photograph.backend.domain.MemberAuthority
import com.photograph.backend.repository.MemberAuthorityRepository
import com.photograph.backend.repository.MemberRepository
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val memberAuthorityRepository: MemberAuthorityRepository
) {
    fun merge(oAuth2User: OAuth2User): Member {
        //todo : 처음 가입한지 확인 후 권한 부여 로직 추가
        val member = Member(
            username = oAuth2User.attributes["name"] as String,
            email = oAuth2User.attributes["email"] as String,
            picture = oAuth2User.attributes["picture"] as String,
            registerDate = LocalDateTime.now()
        )
        return memberRepository.save(member)
    }

    fun findMemberRolesById(id: String): List<MemberAuthority> {
        return memberAuthorityRepository.findMemberRoles(id)
    }
}