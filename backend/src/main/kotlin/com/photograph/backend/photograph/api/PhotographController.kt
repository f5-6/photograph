package com.photograph.backend.photograph.api

import com.photograph.backend.config.security.domain.MemberPrincipal
import com.photograph.backend.photograph.application.PhotographFacade
import jakarta.validation.Valid
import org.springframework.http.MediaType
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PhotographController(private val photographFacade: PhotographFacade) {

    @GetMapping("/api/members/{memberId}/photographs")
    fun get(@PathVariable("memberId") memberId: String) {

    }

    @PostMapping("/admin/photographs}", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun post(@ModelAttribute @Valid request: PostPhotographRequest, @AuthenticationPrincipal member: MemberPrincipal) {

    }

    @PatchMapping("/admin/photographs/{id}")
    fun update(@PathVariable("id") id: String, @AuthenticationPrincipal member: MemberPrincipal) {

    }

    @DeleteMapping("/admin/photographs/{id}")
    fun delete(@PathVariable("id") id: String, @AuthenticationPrincipal member: MemberPrincipal) {

    }
}
