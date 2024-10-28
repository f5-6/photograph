package com.photograph.backend.photograph.api

import com.photograph.backend.photograph.api.controller.PhotographController
import com.photograph.backend.photograph.application.PhotographFacade
import com.photograph.backend.photograph.domain.Photograph
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.test.Test

@WebMvcTest(PhotographController::class)
@AutoConfigureMockMvc(addFilters = false)
class PhotographControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var photographFacade: PhotographFacade

    @Test
    fun 조회() {
        `when`(photographFacade.get(any())).thenReturn(
            listOf(
                Photograph(
                    id = "671dd57173d6ea2c21232c27",
                    memberId = "671dd57173d6ea2c21232c27",
                    url = "test.jpg",
                    description = "abc",
                    tookAt = LocalDate.now(),
                    createdAt = LocalDateTime.now(),
                    updatedAt = null
                )
            )
        )

        mockMvc.perform(
            get("/api/members/{memberId}/photographs", "671dd57173d6ea2c21232c27")
        ).andExpect(status().isOk)
    }
}
