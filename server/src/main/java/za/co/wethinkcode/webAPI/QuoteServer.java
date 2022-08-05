package za.co.wethinkcode.webAPI;

import io.javalin.Javalin;

public class QuoteServer {
    private final Javalin server;

    public QuoteServer() {
        QuoteApiHandler apiHandler = new QuoteApiHandler();

        server = Javalin.create(config -> {
            config.defaultContentType = "application/json";
        });

        this.server.get("/quotes", context -> apiHandler.getAll(context));
        this.server.get("/quote/{id}", context -> apiHandler.getOne(context));
        this.server.post("/quotes", context -> apiHandler.create(context));
        this.server.delete("/quote/{id}", context -> apiHandler.remove(context));

    }

    public static void main(String[] args) {
        QuoteServer server = new QuoteServer();
        server.start(5000);
    }

    public void start(int port) {
        this.server.start(port);
    }

    public void stop() {
        this.server.stop();
    }
}