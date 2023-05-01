package com.example.demo.app.member.handler

import com.example.demo.app.member.model.data.Member
import com.example.demo.app.member.service.MemberService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*


@Component
class MemberHandler(
        private val memberService: MemberService
) {

    suspend fun create(request: ServerRequest): ServerResponse {
        val member = request.awaitBodyOrNull<Member>() ?: throw RuntimeException()
        return memberService.create(member)
                .let { ServerResponse.ok().buildAndAwait() }
    }

    suspend fun findAll(request: ServerRequest): ServerResponse {
        return memberService.findAll()
                .let { ServerResponse.ok().json().bodyAndAwait(it) }
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
