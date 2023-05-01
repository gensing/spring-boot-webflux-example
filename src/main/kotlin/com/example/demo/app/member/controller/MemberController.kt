package com.example.demo.app.member.controller

import com.example.demo.app.member.model.data.Member
import com.example.demo.app.member.service.MemberService
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
    fun findAll(): Flow<Member> {
        return memberService.findAll()
    }

    @GetMapping("/{id}")
    suspend fun findOne(@PathVariable id: Long): ResponseEntity<Member> {
        return memberService.findOne(id)
                ?.let { ResponseEntity.ok(it) }
                ?: ResponseEntity.ok(Member(1, "not found"))
    }

    @PutMapping("/{id}")
    suspend fun update(@PathVariable id: Long, @RequestBody member: Member) {
        return memberService.update(id, member)
    }

    @DeleteMapping("/{id}")
    suspend fun delete(@PathVariable id: Long) {
        return memberService.delete(id)
    }
}