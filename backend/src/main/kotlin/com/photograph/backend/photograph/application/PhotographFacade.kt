package com.photograph.backend.photograph.application

import com.photograph.backend.photograph.application.dto.PostPhotographDTO
import com.photograph.backend.photograph.domain.Photograph
import com.photograph.backend.photograph.domain.PhotographService
import org.springframework.stereotype.Service

@Service
class PhotographFacade(private val photographService: PhotographService) {

    fun post(dto: PostPhotographDTO) {
        // s3 저장 로직
        val url = "test"

        // 저장 로직
        photographService.save(Photograph.create(dto, url))
    }

}
