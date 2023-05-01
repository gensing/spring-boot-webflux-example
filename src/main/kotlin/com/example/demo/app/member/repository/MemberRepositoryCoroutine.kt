package com.example.demo.app.member.repository

import com.example.demo.app.member.model.data.Member
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface MemberRepositoryCoroutine : CoroutineCrudRepository<Member, Long> {
    override suspend fun findById(id: Long): Member?
}