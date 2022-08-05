package za.co.wethinkcode;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.domain.Quote;
import za.co.wethinkcode.webAPI.QuoteServer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuoteApiTest {
    private static QuoteServer server;

    @BeforeAll
    public static void startServer() {
        server = new QuoteServer();
        server.start(5000);
    }

    @AfterAll
    public static void stopServer() {
        server.stop();
    }

    @Test
    @DisplayName("GET /quote/{id}")
    public void getOneQuote() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:5000/quote/1").asJson();
        assertEquals(200, response.getStatus());
        assertEquals("application/json", response.getHeaders().getFirst("Content-Type"));

        JSONObject jsonObject = response.getBody().getObject();
        assertEquals("There is no snooze button on a cat who wants breakfast.", jsonObject.get("body"));
        assertEquals("Unknown", jsonObject.get("author"));
    }

    @Test
    @DisplayName("GET /quote/{invalid id}")
    public void getOneQuoteInvalidID() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:5000/quote/99").asJson();
        assertEquals(404, response.getStatus());
        assertEquals("application/json", response.getHeaders().getFirst("Content-Type"));

        JSONObject jsonObject = response.getBody().getObject();
        assertEquals("Quote not found: 99", jsonObject.get("title"));
    }

    @Test
    @DisplayName("GET /quotes")
    void getAllQuotes() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:5000/quotes").asJson();
        assertEquals(200, response.getStatus());
        assertEquals("application/json", response.getHeaders().getFirst("Content-Type"));

        JSONArray jsonArray = response.getBody().getArray();
        assertTrue(jsonArray.length() > 1);
    }

    @Test
    @DisplayName("POST /quotes")
    void create() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.post("http://localhost:5000/quotes")
                .header("Content-Type", "application/json")
                .body(Quote.create("Meow", "The Cat"))
                .asJson();
        assertEquals(201, response.getStatus());

        response = Unirest.get("http://localhost:5000/quote/4").asJson();
        JSONObject jsonObject = response.getBody().getObject();
        assertEquals("Meow", jsonObject.get("body"));
        assertEquals("The Cat", jsonObject.get("author"));
        assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("POST /quotes{invalid POST}")
    void createInvalid() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.post("http://localhost:5000/quotes")
                .header("Content-Type", "application/json")
                .body("Invalid format for posting a quote")
                .asJson();
        assertEquals(404, response.getStatus());

        JSONObject jsonObject = response.getBody().getObject();
        assertEquals("Failed to create quote", jsonObject.get("title"));
    }
}
