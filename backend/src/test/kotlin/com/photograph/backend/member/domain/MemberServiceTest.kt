package com.photograph.backend.member.domain

import com.photograph.backend.member.infra.MemberDocument
import com.photograph.backend.member.infra.MemberRepository
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class MemberServiceTest : DescribeSpec({

    val memberRepository: MemberRepository = mockk()
    val entity: MemberDocument = mockk(relaxed = true)
    val member = Member("정명", "jeongmyeong@gmail.com", "google", "google-sub")

    describe("회원정보 서비스는") {
        val service = MemberService(memberRepository)

        context("회원정보가 있으면") {
            every { memberRepository.findByName(any()) } returns entity

            service.merge(member)

            it("그대로 반환한다") {
                verify(exactly = 0) { memberRepository.save(any()) }
            }
        }

        context("회원정보가 없으면") {
            every { memberRepository.findByName(any()) } returns null
            every { memberRepository.save(any()) } returns entity

            service.merge(member)

            it("회원정보를 새로 저장한다") {
                verify(exactly = 1) { memberRepository.save(any()) }
            }
        }
    }
})
