package com.photograph.backend.controller

import com.photograph.backend.domain.Member
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/members")
class MemberController {

    @GetMapping("/me")
    fun getMembers(@AuthenticationPrincipal member: Member): Member {
        return member
    }

}