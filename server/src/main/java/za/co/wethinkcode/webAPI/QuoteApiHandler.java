package za.co.wethinkcode.webAPI;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import io.javalin.http.NotFoundResponse;
import za.co.wethinkcode.data.QuoteDO;
import za.co.wethinkcode.data.QuoteDatabase;

import java.sql.SQLException;

public class QuoteApiHandler {
    private QuoteDatabase database;

    public QuoteApiHandler() {
        try {
            database = new QuoteDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all quotes
     *
     * @param context The Javalin Context for the HTTP GET Request
     */
    public void getAll(Context context) {
        context.json(database.all());
    }

    /**
     * Get one quote
     *
     * @param context The Javalin Context for the HTTP GET Request
     */
    public void getOne(Context context) {
        Integer id = context.pathParamAsClass("id", Integer.class).get();
        QuoteDO quote = database.get(id);
        if (quote == null) {
            throw new NotFoundResponse("Quote not found: " + id);
        }
        context.json(quote);
    }

    /**
     * Create a new quote
     *
     * @param context The Javalin Context for the HTTP POST Request
     */
    public void create(Context context) {
        try {
            QuoteDO quote = context.bodyAsClass(QuoteDO.class);
            QuoteDO newQuote = database.add(quote.getBody(), quote.getAuthor());
            context.header("Location", "/quote/" + newQuote.getId());
            context.status(HttpCode.CREATED);
            context.json(database.all());
        } catch (Exception e) {
            throw new NotFoundResponse("Failed to create quote");
        }
    }

    public void remove(Context context) {
        Integer id = context.pathParamAsClass("id", Integer.class).get();
        database.removeQuote(id);
        context.json(database.all());
    }
}
