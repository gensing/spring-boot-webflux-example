package com.example.demo.member.handler

import com.example.demo.member.model.data.Member
import com.example.demo.member.service.MemberService
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*


@Configuration
class MemberHandler(
        private val memberService: MemberService
) {

    suspend fun create(request: ServerRequest): ServerResponse {
        val member = request.awaitBodyOrNull<Member>() ?: throw RuntimeException()
        return memberService.create(member)
                .let { ServerResponse.ok().buildAndAwait() }
    }

    suspend fun findAll(request: ServerRequest): ServerResponse {
        return ServerResponse.ok().json().bodyValueAndAwait(memberService.findAll())
    }

    suspend fun findOne(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        return memberService.findOne(id)
                ?.let { ServerResponse.ok().json().bodyValueAndAwait(it) }
                ?: ServerResponse.notFound().buildAndAwait();
    }

    suspend fun update(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        val member = request.awaitBodyOrNull<Member>() ?: throw RuntimeException()
        return memberService.update(id, member)
                .let { ServerResponse.ok().buildAndAwait() }
    }

    suspend fun delete(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        return memberService.delete(id)
                .let { ServerResponse.ok().buildAndAwait() }
    }
}
