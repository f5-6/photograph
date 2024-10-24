package com.photograph.backend.photograph.api

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.slf4j.LoggerFactory
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.nio.file.Files

class ImageFileValidator : ConstraintValidator<ImageFile, MultipartFile> {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun isValid(
        value: MultipartFile,
        context: ConstraintValidatorContext?,
    ): Boolean {
        val file = File(value.originalFilename ?: throw NullPointerException())
        var type = ""

        try {
            type = Files.probeContentType(file.toPath())
        } catch (e: IOException) {
            log.error("image type error", e)
        }

        return type.startsWith("image")
    }
}
