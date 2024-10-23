package com.photograph.backend.config.security.component

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import jakarta.servlet.http.HttpServletResponse
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

class Oauth2LogoutHandlerTest : DescribeSpec({
    describe("Oauth2LogoutHandler 는") {
        val handler = Oauth2LogoutHandler()

        context("로그아웃에 성공하면") {
            val request = MockHttpServletRequest()
            val response = MockHttpServletResponse()
            val authentication = mockk<Authentication>()

            handler.onLogoutSuccess(request, response, authentication)

            it("200응답을 반환한다") {
                response.status shouldBe HttpServletResponse.SC_OK
            }

            it("JSESSIONID 쿠키를 삭제한다") {
                response.cookies.find { it.name == "JSESSIONID" }?.maxAge shouldBe 0
            }

            it("SecurityContext 를 삭제한다") {
                SecurityContextHolder.getContext().authentication shouldBe null
            }
        }
    }
})
