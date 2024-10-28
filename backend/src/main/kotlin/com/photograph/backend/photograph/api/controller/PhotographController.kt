package com.photograph.backend.photograph.api.controller

import com.photograph.backend.photograph.application.PhotographFacade
import com.photograph.backend.photograph.domain.Photograph
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PhotographController(private val photographFacade: PhotographFacade) {

    @GetMapping("/members/{memberId}/photographs")
    fun get(@PathVariable("memberId") memberId: String): List<Photograph> = photographFacade.get(memberId)
}
