package com.photograph.backend.config.security.component

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.security.core.Authentication

class AuthenticationSuccessHandlerTest : DescribeSpec({

    val redirectUrl = "http://localhost:5173"

    describe("AuthenticationSuccessHandler 는") {
        val handler = AuthenticationSuccessHandler(redirectUrl)

        context("로그인에 성공하면") {
            val request = MockHttpServletRequest()
            val response = MockHttpServletResponse()
            val authentication = mockk<Authentication>()

            handler.onAuthenticationSuccess(request, response, authentication)

            it("리다이렉트한다") {
                response.getHeader("Location") shouldBe redirectUrl
            }
        }
    }
})
