package com.photograph.backend.photograph.infra

import com.photograph.backend.photograph.application.FileNameHasher
import io.awspring.cloud.s3.ObjectMetadata
import io.awspring.cloud.s3.S3Template
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.services.s3.model.ObjectCannedACL

@Component
class S3ImageHandler(
    private val s3Template: S3Template,
    @Value("\${spring.cloud.aws.s3.bucket}")
    private val bucketName: String,
) {
    fun uploadImageToS3(uploadImageFile: MultipartFile): String {
        val fileName = FileNameHasher.generateFileName(uploadImageFile)

        val objectMetadata =
            ObjectMetadata
                .builder()
                .contentType(uploadImageFile.contentType)
                .acl(ObjectCannedACL.PUBLIC_READ)
                .build()

        uploadImageFile.inputStream.use {
            s3Template.upload(
                bucketName,
                fileName,
                it,
                objectMetadata
            )
        }

        return "https://$bucketName.s3.ap-northeast-2.amazonaws.com/$fileName"
    }
}
