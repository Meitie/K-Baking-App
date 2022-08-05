package za.co.wethinkcode.domain;

public class Quote {
    private String body;
    private String author;

    public void setBody(String body) {
        this.body = body;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Use this convenient factory method to create a new quote.
     * Warning the ID will be null!
     *
     * @param body   the text of the quote
     * @param author the name of the person that said the text
     * @return a Quote object
     */
    public static Quote create(String body, String author) {
        Quote quote = new Quote();
        quote.setBody(body);
        quote.setAuthor(author);
        return quote;
    }
}
