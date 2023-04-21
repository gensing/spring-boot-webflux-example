package com.example.demo.member.handler

import com.example.demo.member.model.data.Member
import com.example.demo.member.repository.MemberRepositoryCoroutine
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*
import java.lang.RuntimeException


@Configuration
class MemberHandler(
        private val repository: MemberRepositoryCoroutine
) {

    suspend fun findAll(request: ServerRequest): ServerResponse {
        return ServerResponse.ok().json().bodyValueAndAwait(repository.findAll())
    }

    suspend fun findOne(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        return repository.findById(id)?.let { ServerResponse.ok().json().bodyValueAndAwait(it) }
                ?: ServerResponse.notFound().buildAndAwait();
    }

    suspend fun create(request: ServerRequest): ServerResponse {
        val member = request.awaitBodyOrNull<Member>() ?: throw RuntimeException()
        return repository.save(member).let { ServerResponse.ok().buildAndAwait() }
    }

    suspend fun update(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        val member = request.awaitBodyOrNull<Member>() ?: throw RuntimeException()
        return repository.save(Member(id, member.username)).let { ServerResponse.ok().buildAndAwait() }
    }

    suspend fun delete(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        return repository.deleteById(id).let { ServerResponse.ok().buildAndAwait() }
    }
}
