package io.fairboi.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json

internal val mainHttpClient get() = HttpClient(CIO) {
    expectSuccess = true
    followRedirects = false


    install(HttpRequestRetry) {
        retryOnServerErrors(maxRetries = 5)
        exponentialDelay()
    }

    install(DefaultRequest) {
        contentType(ContentType.Application.Json)
    }
    install(ContentNegotiation) {
       json()
    }


}