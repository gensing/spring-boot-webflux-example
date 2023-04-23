package com.example.demo.member.controller

import com.example.demo.member.model.data.Member
import com.example.demo.member.service.MemberService
import kotlinx.coroutines.flow.Flow
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/members")
@RestController
class MemberController(
        private val memberService: MemberService
) {

    @PostMapping("")
    suspend fun create(@RequestBody member: Member): ResponseEntity<Member> {
        return memberService.create(member)
                .let { ResponseEntity.ok().build() }
    }

    @GetMapping("")
    suspend fun findAll(@PathVariable id: Long): ResponseEntity<Flow<Member>> {
        return memberService.findAll()
                .let { ResponseEntity.ok(it) }
    }

    @GetMapping("/{id}")
    suspend fun findOne(@PathVariable id: Long): ResponseEntity<Member> {
        return memberService.findOne(id)
                ?.let { ResponseEntity.ok(it) }
                ?: ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    suspend fun update(@PathVariable id: Long, @RequestBody member: Member): ResponseEntity<Any> {
        return memberService.update(id, member)
                .let { ResponseEntity.ok().build() }
    }

    @DeleteMapping("/{id}")
    suspend fun delete(@PathVariable id: Long): ResponseEntity<Any> {
        return memberService.delete(id)
                .let { ResponseEntity.ok().build() }
    }
}