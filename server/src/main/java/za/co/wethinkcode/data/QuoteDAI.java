package za.co.wethinkcode.data;

import net.lemnik.eodsql.BaseQuery;
import net.lemnik.eodsql.Select;
import net.lemnik.eodsql.Update;

import java.util.List;

public interface QuoteDAI extends BaseQuery {

    @Select(
            "SELECT * FROM quote"
    )
    List<QuoteDO> getAll();


    @Select(
            "SELECT * FROM quote WHERE id = ?{1}"
    )
    QuoteDO getID(int id);

    @Update(
            "INSERT INTO quote(id, body, author) VALUES (?{1}, ?{2}, ?{3})"
    )
    void addQuote(int id, String body, String author);
}
