package com.photograph.backend.config.security.service

import com.photograph.backend.member.service.MemberService
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class OauthUserService(
    private val memberService: MemberService,
) : DefaultOAuth2UserService() {

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val oAuth2User: OAuth2User = super.loadUser(userRequest)
        return memberService.merge(oAuth2User)
    }
}
