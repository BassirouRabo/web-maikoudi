package plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.request.*
import org.slf4j.event.Level

fun Application.configureMonitoring() {
    install(CallLogging) {
        level = Level.INFO

        filter { call -> !call.request.path().startsWith("/assets") && call.request.path() != "/health" }

        format { call ->
            val status = call.response.status()?.value
            val httpMethod = call.request.httpMethod.value
            val host = call.request.host()
            val path = call.request.path()
            val headers = call.request.headers
            val userAgent = headers["User-Agent"] ?: ""
            val country = headers["CloudFront-Viewer-Country-Name"] ?: ""
            val city = headers["CloudFront-Viewer-City"] ?: ""
            val state = headers["CloudFront-Viewer-Country-Region-Name"] ?: ""
            val remoteHost = call.request.origin.remoteHost
            val ip = headers["X-Forwarded-For"]?.split(",")?.map { it.trim() }?.firstOrNull { it.isNotEmpty() } ?: remoteHost
            "[Status: $status] [Method: $httpMethod] [Path: $path] [Host: $host] [Ip: $ip] [Country: $country] [State: $state] [City: $city] [User Agent: $userAgent]"
        }
    }
}