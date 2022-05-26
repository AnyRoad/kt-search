package com.jillesvangurp.ktsearch

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*

actual fun defaultKtorHttpClient(logging: Boolean): HttpClient {
    return HttpClient(CIO) {
        engine {
            maxConnectionsCount = 100
            endpoint {
                keepAliveTime = 100_000
                connectTimeout = 5_000
                requestTimeout = 30_000
                connectAttempts = 3
            }
        }
        if(logging) {
            install(Logging) {
                level = LogLevel.ALL
            }
        }
        install(ContentNegotiation) {
            json()
        }
    }
}