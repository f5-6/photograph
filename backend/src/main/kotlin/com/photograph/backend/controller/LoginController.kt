package com.photograph.backend.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController {

    @GetMapping("/login/oauth2/google")
    fun login(@RequestParam("code") authorizationCode: String) {
        println(authorizationCode)
    }
}
