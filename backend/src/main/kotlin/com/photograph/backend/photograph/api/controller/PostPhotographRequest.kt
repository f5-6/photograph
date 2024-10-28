package com.photograph.backend.photograph.api.controller

import com.photograph.backend.photograph.api.ImageFile
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate

data class PostPhotographRequest(
    @field:ImageFile
    val image: MultipartFile,
    val description: String,
    val tookAt: LocalDate,
)
