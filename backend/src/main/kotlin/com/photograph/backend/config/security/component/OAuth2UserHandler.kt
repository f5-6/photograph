package com.photograph.backend.config.security.component

import com.photograph.backend.config.security.domain.MemberPrincipal
import com.photograph.backend.member.domain.Member
import com.photograph.backend.member.domain.MemberService
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Component

@Component
class OAuth2UserHandler(private val memberService: MemberService) : DefaultOAuth2UserService() {

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val oAuth2User = super.loadUser(userRequest)
        val provider = userRequest.clientRegistration.clientName

        return memberService.merge(Member.create(oAuth2User, provider))
            .run { MemberPrincipal(this) }
    }
}
