package com.photograph.backend.photograph.application

import org.springframework.web.multipart.MultipartFile
import java.math.BigInteger
import java.security.MessageDigest

object FileNameHasher {

    fun generateFileName(uploadImageFile: MultipartFile): String {
        val uploadImageByte = uploadImageFile.bytes
        val md5 = MessageDigest.getInstance("MD5")
        val digest = md5.digest(uploadImageByte)

        val extension = getFileExtension(uploadImageFile)

        return BigInteger(1, digest).toString(16) + "." + extension
    }

    private fun getFileExtension(file: MultipartFile): String? {
        val originalFilename = file.originalFilename ?: return null

        return if (originalFilename.contains('.')) {
            originalFilename.substringAfterLast('.')
        } else {
            null
        }
    }
}
