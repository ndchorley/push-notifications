object ConsoleClient : Client {
    override fun notify(name: String) {
        println("$name is here")
    }
}
