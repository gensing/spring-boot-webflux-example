package com.example.demo.config

import com.example.demo.app.member.handler.MemberHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RouterConfig(
        private val memberHandler: MemberHandler
) {

    @Bean
    fun coRouter() = coRouter {
        ("/co/members" and accept(APPLICATION_JSON)).nest {
            GET("") { memberHandler.findAll(it) }
            GET("/{id}") { memberHandler.findOne(it) }
            POST("") { memberHandler.create(it) }
            PUT("/{id}") { memberHandler.update(it) }
            DELETE("/{id}") { memberHandler.delete(it) }
        }
    }
}
