package com.example.demo.member.repository

import com.example.demo.member.model.data.Member
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface MemberRepositoryCoroutine : CoroutineCrudRepository<Member, Long> {
    override suspend fun findById(id: Long): Member?
}