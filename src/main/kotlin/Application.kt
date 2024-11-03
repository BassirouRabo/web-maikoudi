import io.ktor.server.application.*
import plugins.configureMonitoring
import plugins.configureRouting
import plugins.configureSerialization
import plugins.configureTemplating

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureTemplating()
    configureRouting()
    configureMonitoring()
    configureSerialization()
}
