import org.http4k.core.Method.POST
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer

fun main() {
    val routes = routes(
        "/here/{name}" bind POST to { 
            request ->
                val name = request.path("name")!!
            
                notifyClientsAbout(name)
                
                Response(OK)
        }
    )

    routes.asServer(Jetty(port = 9000)).start()
}

fun notifyClientsAbout(name: String) {
    val clients: List<Client> = listOf(ConsoleClient)
    
    clients.forEach { client -> client.notify(name) }
}
