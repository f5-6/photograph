package com.photograph.backend.photograph.api.controller

import com.photograph.backend.config.security.domain.MemberPrincipal
import com.photograph.backend.photograph.application.PhotographFacade
import com.photograph.backend.photograph.application.dto.PostPhotographDTO
import jakarta.validation.Valid
import org.springframework.http.MediaType
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class PhotographAdminController(private val photographFacade: PhotographFacade) {

    @PostMapping("/photographs", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
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
