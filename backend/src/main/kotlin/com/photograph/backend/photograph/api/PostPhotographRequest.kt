package com.photograph.backend.photograph.api

import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate

data class PostPhotographRequest(
    @field:ImageFile
    val image: MultipartFile,
    val description: String,
    val tookAt: LocalDate,
)
