package com.example.demo.app.member.service

import com.example.demo.app.member.model.data.Member
import com.example.demo.app.member.repository.MemberRepositoryCoroutine
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service

@Service
class MemberService(private val repository: MemberRepositoryCoroutine) {

    fun findAll(): Flow<Member> {
        return repository.findAll()
    }

    suspend fun findOne(id: Long): Member? {
        return repository.findById(id)
    }

    suspend fun create(member: Member): Long {
        return repository.save(member).id
    }

    suspend fun update(id: Long, member: Member) {
        repository.save(Member(id, member.username))
    }

    suspend fun delete(id: Long) {
        repository.deleteById(id)
    }

}