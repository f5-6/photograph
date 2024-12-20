package com.photograph.backend.photograph.api.controller

import com.photograph.backend.config.security.domain.MemberPrincipal
import com.photograph.backend.photograph.application.PhotographFacade
import com.photograph.backend.photograph.application.dto.PostPhotographDTO
import com.photograph.backend.photograph.application.dto.RemovePhotographDTO
import jakarta.validation.Valid
import org.springframework.http.MediaType
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
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

    @GetMapping("/photographs")
    fun post(@AuthenticationPrincipal member: MemberPrincipal) = photographFacade.get(member.attributes["id"] as String)

    @DeleteMapping("/photographs/{id}")
    fun remove(@AuthenticationPrincipal member: MemberPrincipal, @PathVariable("id") id: String) =
        photographFacade.remove(
            RemovePhotographDTO(member.attributes["id"] as String, id)
        )
}
