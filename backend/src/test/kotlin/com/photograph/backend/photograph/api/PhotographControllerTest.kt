package com.photograph.backend.photograph.api

import com.photograph.backend.config.security.component.OAuth2UserHandler
import com.photograph.backend.config.security.domain.MemberPrincipal
import com.photograph.backend.member.domain.Member
import com.photograph.backend.photograph.application.PhotographFacade
import org.mockito.Mockito.doNothing
import org.mockito.kotlin.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.Test

@WebMvcTest(PhotographController::class)
class PhotographControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var photographFacade: PhotographFacade

    @MockBean
    private lateinit var oAuth2UserHandler: OAuth2UserHandler

    @Test
    fun test_file_type() {
        doNothing().`when`(photographFacade).post(any())

        val oAuth2User: OAuth2User = MemberPrincipal(
            Member(
                id = "14", name = "jeongmyeong", email = "test@test.com", provider = "google", providerKey = "afdsd"
            )
        )

        val file =
            MockMultipartFile(
                "image",
                "image.html",
                MediaType.TEXT_HTML_VALUE,
                ByteArray(1 * 1024 * 1024)
            )

        mockMvc.perform(
            multipart("/admin/photographs")
                .file(file)
                .param("description", "T_T")
                .param("tookAt", "2020-10-01")
                .with(oauth2Login().oauth2User(oAuth2User))
                .with(csrf()) // Security config 에서 비활성화 해두어도, 추가해야함 ;;
        ).andExpect(status().is4xxClientError)
    }

    @Test
    fun test_file_size() {
        doNothing().`when`(photographFacade).post(any())

        val oAuth2User: OAuth2User = MemberPrincipal(
            Member(
                id = "14", name = "jeongmyeong", email = "test@test.com", provider = "google", providerKey = "afdsd"
            )
        )

        val file =
            MockMultipartFile(
                "image",
                "image.png",
                MediaType.IMAGE_PNG_VALUE,
                ByteArray(2 * 1024 * 1024)
            )

        mockMvc.perform(
            multipart("/admin/photographs")
                .file(file)
                .param("description", "T_T")
                .param("tookAt", "2020-10-01")
                .with(oauth2Login().oauth2User(oAuth2User))
                .with(csrf()) // Security config 에서 비활성화 해두어도, 추가해야함 ;;
        ).andExpect(status().is4xxClientError)
    }

    @Test
    fun test() {
        doNothing().`when`(photographFacade).post(any())

        val oAuth2User: OAuth2User = MemberPrincipal(
            Member(
                id = "14", name = "jeongmyeong", email = "test@test.com", provider = "google", providerKey = "afdsd"
            )
        )

        val file =
            MockMultipartFile(
                "image",
                "image.png",
                MediaType.IMAGE_PNG_VALUE,
                ByteArray(1 * 1024 * 1024)
            )

        mockMvc.perform(
            multipart("/admin/photographs")
                .file(file)
                .param("description", "T_T")
                .param("tookAt", "2020-10-01")
                .with(oauth2Login().oauth2User(oAuth2User))
                .with(csrf()) // Security config 에서 비활성화 해두어도, 추가해야함 ;;
        ).andExpect(status().isOk)
    }
}
