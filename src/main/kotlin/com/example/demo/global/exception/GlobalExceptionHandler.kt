package com.example.demo.global.exception

import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.http.MediaType
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Mono

@Component
class GlobalExceptionHandler(
        errorAttributes: ErrorAttributes,
        applicationContext: ApplicationContext,
        serverCodecConfigurer: ServerCodecConfigurer
) : AbstractErrorWebExceptionHandler(
        errorAttributes,
        WebProperties.Resources(),
        applicationContext
) {

    companion object {

    }

    init {
        setMessageWriters(serverCodecConfigurer.writers)
        setMessageReaders(serverCodecConfigurer.readers)
    }

    override fun getRoutingFunction(errorAttributes: ErrorAttributes): RouterFunction<ServerResponse> {
        return RouterFunctions.route(RequestPredicates.all()) { renderErrorResponse(it) }
    }

    private fun renderErrorResponse(request: ServerRequest): Mono<ServerResponse> {
        return getErrorAttributes(request, ErrorAttributeOptions.defaults()).let {
            ServerResponse.status(500)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(it))
        }
    }
}