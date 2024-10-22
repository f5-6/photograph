package com.photograph.backend.config.security

import com.photograph.backend.config.security.domain.MemberPrincipal
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController {

    @GetMapping("/admin")
    fun test(@AuthenticationPrincipal member: MemberPrincipal): MemberPrincipal {
        return member
    }

    @GetMapping("/permit")
    fun test(): String {
        return "permit"
    }

    @GetMapping("/api/test")
    fun testApi(): String {
        return "api"
    }
}
