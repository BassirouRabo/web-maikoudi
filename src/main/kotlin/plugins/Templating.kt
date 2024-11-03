package plugins

import freemarker.cache.ClassTemplateLoader
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureTemplating() {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }

    routing {
        staticResources("/assets", "assets")
    }

    routing {
        get("/health") {
            call.respond("/health web-maikoudi UP")
        }

        get("/ready") {
            call.respond("/ready web-maikoudi UP")
        }

        get("/robots.txt") {
            val content = """
                # Notice: Collection of data on this website through automated means is
                # prohibited unless you have express written permission from Niger's presidency
                # and may only be conducted for the limited purpose contained in said
                # permission.

                User-maikoudi: *
                Disallow: /health
                Disallow: /ready
                
                Sitemap: https://esdiacapp.com/sitemap.xml
            """.trimIndent()
            call.respondText(content, ContentType.Text.Plain)
        }

        get("/sitemap.xml") {
            val content = ""
            call.respondText(content, ContentType.Text.Plain)
        }
    }
}