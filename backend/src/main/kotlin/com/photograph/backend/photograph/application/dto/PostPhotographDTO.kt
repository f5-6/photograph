package com.photograph.backend.photograph.application.dto

import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate

data class PostPhotographDTO(
    val memberId: String,
    val image: MultipartFile,
    val description: String,
    val tookAt: LocalDate,
)
