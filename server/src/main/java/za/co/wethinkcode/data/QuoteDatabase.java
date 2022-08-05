package za.co.wethinkcode.data;

import net.lemnik.eodsql.QueryTool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuoteDatabase {
    public final String dbUrl = "jdbc:sqlite:quoteDB.db";
    public final Connection connection;
    public final QuoteDAI quoteQuery;

    public QuoteDatabase() throws SQLException {
        this.connection = DriverManager.getConnection(dbUrl);
        this.quoteQuery = QueryTool.getQuery(this.connection, QuoteDAI.class);
    }

    public QuoteDO get(Integer id) {
        return quoteQuery.getID(id);
    }

    public List<QuoteDO> all() {
        return quoteQuery.getAll();
    }

    public QuoteDO add(String body, String author) {
        int id = generateNewID();
        quoteQuery.addQuote(id, body, author);
        return quoteQuery.getID(id);
    }

    public void removeQuote(Integer id) {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM quote WHERE id = ?")) {
            stmt.setString(1, String.valueOf(id));
            stmt.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private int generateNewID() {
        List<QuoteDO> currentQuotes = all();
        ArrayList<Integer> currentIDs = new ArrayList<>();

        for (QuoteDO quote : currentQuotes) {
            currentIDs.add(quote.getId());
        }

        int newID = 1;
        while (currentIDs.contains(newID)) {
            newID++;
        }

        return newID;
    }
}
