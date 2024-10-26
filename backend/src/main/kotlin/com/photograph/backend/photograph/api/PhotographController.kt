package com.photograph.backend.photograph.api

import com.photograph.backend.config.security.domain.MemberPrincipal
import com.photograph.backend.photograph.application.PhotographFacade
import com.photograph.backend.photograph.application.dto.PostPhotographDTO
import com.photograph.backend.photograph.domain.Photograph
import jakarta.validation.Valid
import org.springframework.http.MediaType
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PhotographController(private val photographFacade: PhotographFacade) {

    @GetMapping("/api/members/{memberId}/photographs")
    fun get(@PathVariable("memberId") memberId: String): List<Photograph> {
        return photographFacade.get(memberId)
    }

    @PostMapping("/admin/photographs", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun post(
        @Valid @ModelAttribute request: PostPhotographRequest,
        @AuthenticationPrincipal member: MemberPrincipal
    ) {
        photographFacade.post(
            PostPhotographDTO(
                memberId = member.attributes["id"] as String,
                image = request.image,
                description = request.description,
                tookAt = request.tookAt
            )
        )
    }
}
