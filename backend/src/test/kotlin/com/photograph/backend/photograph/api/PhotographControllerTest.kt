package com.photograph.backend.photograph.api

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(PhotographControllerTest::class)
class PhotographControllerTest(@Autowired val mockMvc: MockMvc) {

//    : DescribeSpec({

    @Test
    @WithMockUser(roles = ["ADMIN"])
    fun test() {
        val file = MockMultipartFile(
            "image",
            "test.txt",
            MediaType.TEXT_PLAIN_VALUE,
            ByteArray(1 * 1024 * 1024)
        )

        val a = SecurityContextHolder.getContext().authentication

        val res = mockMvc.perform(
            multipart("/admin/photographs")
                .file(file)
                .param("description", "T_T")
                .param("tookAt", "2020-10-01")
                .contentType(MediaType.MULTIPART_FORM_DATA)
        )

        res.andExpect(status().isOk)
    }

//    describe("요청시") {
//        val userDetails = User.withUsername("testUser")
//            .password("password")
//            .roles("ADMIN")
//            .build()
//
//        context("확장자가 이미지가 아닌 경우") {
//            it("오류가 발생한다") {
//
//            }
//        }
//
//        context("파일 크기가 너무 크면") {
//            it("오류가 발생한다") {
//                val file =
//                    MockMultipartFile(
//                        "image",
//                        "image.png",
//                        MediaType.IMAGE_PNG_VALUE,
//                        ByteArray(1 * 1024 * 1024)
//                    )
//
//                mockMvc.perform(
//                    multipart("/admin/photographs")
//                        .file(file)
//                        .param("description", "T_T")
//                        .param("tookAt", "2020-10-01")
//                ).andExpect(status().isOk)
//            }
//        }
//    }
}
