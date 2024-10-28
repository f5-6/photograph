package com.photograph.backend.photograph.application

import com.photograph.backend.photograph.application.dto.PostPhotographDTO
import com.photograph.backend.photograph.application.dto.RemovePhotographDTO
import com.photograph.backend.photograph.domain.Photograph
import com.photograph.backend.photograph.domain.PhotographService
import org.springframework.stereotype.Service

@Service
class PhotographFacade(private val photographService: PhotographService) {

    fun post(dto: PostPhotographDTO) {
        // s3 저장 로직
        val url = "test.com/image.jpg"

        // 저장 로직
        photographService.save(Photograph.create(dto, url))
    }

    fun get(memberId: String) = photographService.get(memberId)

    fun remove(dto: RemovePhotographDTO) {
        //s3 remove
        
        val photograph = photographService.getById(dto.photographId)

        if (photograph.memberId != dto.memberId) {
            throw IllegalArgumentException("올바르지 않은 요청입니다.")
        }

        photographService.remove(dto.photographId)
    }
}
