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
        val authentication = mockk<Authentication>()

        context("로그인에 성공하였을 경우") {
            context("statue 파라미터에 redirectUrl 이 포함되어 있을 경우") {
                val request = MockHttpServletRequest()
                val response = MockHttpServletResponse()
                request.addParameter("state", "?redirectUrl=/prev")

                handler.onAuthenticationSuccess(request, response, authentication)

                it("리다이렉트한다") {
                    response.getHeader("Location") shouldBe "$redirectUrl/prev"
                }
            }

            context("statue 파라미터에 redirectUrl 이 포함되어 있지 않을 경우") {
                val request = MockHttpServletRequest()
                val response = MockHttpServletResponse()

                handler.onAuthenticationSuccess(request, response, authentication)

                it("리다이렉트한다") {
                    response.getHeader("Location") shouldBe "$redirectUrl/admin"
                }
            }
        }
    }
})
