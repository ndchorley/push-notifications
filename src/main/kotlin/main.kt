import org.http4k.core.Method.*
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.poly
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer
import java.nio.file.Files
import java.nio.file.Paths

fun main() {
    val routes = routes(personIsHere, showPage)

    poly(routes).asServer(Jetty(port = 9000)).start()
}

val personIsHere =
    "/here/{name}" bind POST to { request ->
        val name = request.path("name")!!

        notifyClientsAbout(name)

        Response(OK)
    }

fun notifyClientsAbout(name: String) {
    val clients: List<Client> = listOf(ConsoleClient)

    clients.forEach { client -> client.notify(name) }
}

val showPage =
    "/" bind GET to { _ ->
        Response(OK).body(page())
    }

fun page(): String {
    return Files.readString(
        Paths.get("src/main/resources/page.html")
    )
}
