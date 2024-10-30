package com.photograph.backend.photograph.application

import com.photograph.backend.photograph.application.dto.RemovePhotographDTO
import com.photograph.backend.photograph.domain.PhotographService
import com.photograph.backend.photograph.infra.S3ImageHandler
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.every
import io.mockk.mockk

class PhotographFacadeTest : DescribeSpec({
    val service: PhotographService = mockk()
    val s3ImageHandler: S3ImageHandler = mockk()
    val facade = PhotographFacade(service, s3ImageHandler)

    describe("삭제시에") {
        val dto = RemovePhotographDTO("memberId", "photographId")

        context("본인의 사진이 아니면") {
            every { service.getById("photographId") } returns mockk {
                every { memberId } returns "wrongMember"
            }

            it("예외를 반환한다") {
                shouldThrow<IllegalArgumentException> {
                    facade.remove(dto)
                }
            }
        }
    }
})
