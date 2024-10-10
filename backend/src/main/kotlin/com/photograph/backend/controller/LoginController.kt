package com.photograph.backend.controller

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController {

    @GetMapping("/login/oauth2/google")
    fun login(@RequestParam("code") authorizationCode: String) {
        println(authorizationCode)
    }

    @GetMapping("/user")
    fun user(@AuthenticationPrincipal user: OAuth2User): OAuth2User {
        return user
    }
}
