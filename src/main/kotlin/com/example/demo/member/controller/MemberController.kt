package com.example.demo.member.controller

import com.example.demo.member.data.Member
import com.example.demo.member.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(private val memberService: MemberService) {

    @GetMapping("/members/{id}")
    suspend fun getUserById(@PathVariable id: Long): ResponseEntity<Member> {
        val user = memberService.findOne(id)
        return if (user != null) ResponseEntity.ok(user) else ResponseEntity.notFound().build()
    }
}