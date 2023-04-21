package com.example.demo.member.handler

import com.example.demo.member.repository.MemberRepositoryCoroutine
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*


@Configuration
class MemberHandler(
        private val repository: MemberRepositoryCoroutine
) {

    suspend fun findOne(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        return repository.findById(id)?.let { ServerResponse.ok().json().bodyValueAndAwait(it) }
                ?: ServerResponse.notFound().buildAndAwait();
    }
}
