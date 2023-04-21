package com.example.demo.member.model.data

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("members")
data class Member(
        @Id val id: Long,
        @Column("username") val username: String
) {

}
