package com.example.demo.member.service

import com.example.demo.member.model.data.Member
import com.example.demo.member.repository.MemberRepositoryCoroutine
import org.springframework.stereotype.Service

@Service
class MemberService(val repository: MemberRepositoryCoroutine) {

    suspend fun findOne(id: Long): Member? {
        return repository.findById(id)
    }

}