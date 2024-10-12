package com.photograph.backend.member.repository

import com.photograph.backend.member.domain.Member
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MongoMemberRepository : MongoRepository<Member, String>
