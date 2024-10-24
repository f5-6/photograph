package com.photograph.backend.photograph.domain

import com.photograph.backend.photograph.infra.PhotographRepository
import org.springframework.stereotype.Service

@Service
class PhotographService(private val photographRepository: PhotographRepository) {

}
