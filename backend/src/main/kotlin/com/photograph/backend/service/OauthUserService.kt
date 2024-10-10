package com.photograph.backend.service

import com.photograph.backend.domain.MemberDetails
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service


@Service
class OauthUserService(
    private val memberService: MemberService,
) : OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private val delegate = DefaultOAuth2UserService()

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val oAuth2User: OAuth2User = delegate.loadUser(userRequest)
        val member = memberService.merge(oAuth2User)
        return memberService.findMemberRolesById(member.memberId)
            .run { MemberDetails(member, this, oAuth2User.attributes) }
    }
}