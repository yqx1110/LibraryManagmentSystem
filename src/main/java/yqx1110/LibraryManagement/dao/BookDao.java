package yqx1110.LibraryManagement.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import yqx1110.LibraryManagement.entity.Book;

import java.util.List;

@Component
public class BookDao {
    private JdbcTemplate jdbcTemplate;
    private static final RowMapper<Book> bookRowMapper = ((rs, i) -> {
       return new Book(rs.getInt("book_id"), rs.getString("name"),
               rs.getString("author"), rs.getString("publish"),
               rs.getString("ISBN"), rs.getString("introduction"),
               rs.getString("language"), rs.getDouble("price"),
               rs.getDate("pubdate"), rs.getInt("class_id"),
               rs.getInt("pressmark"), rs.getInt("state")

       );
    });

    @Autowired
    public void setJdbcTemplate(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    private static final String GET_BOOK_SQL = "select * from book_info where book_id = ?";
    private static final String GET_BOOKS_SQL = "select * from book_info limit ? offset ?";
    private static final String ADD_BOOK_SQL = "insert into book_info values(NULL,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String DELETE_BOOK_SQL = "delete from book_info where book_id = ?";
    private static final String SEARCH_BOOKS_SQL = "select * from book_info where name like ?" +
            "or author like ? limit ? offset ?";
    private static final String SEARCH_RESULT_COUNT_SQL = "";
    private static final String REVISE_BOOK_INFO_SQL = "update book_info set name= ?, author= ?, " +
            "publish= ?, ISBN= ?, introduction= ?, language= ?, price= ?, " +
            "pubdate= ?, class_id= ?, pressmark= ?, state= ? where book_id= ?";

    public Book getBookById(int bookId) {
        return jdbcTemplate.queryForObject(GET_BOOK_SQL, bookRowMapper, bookId);
    }

    public List<Book> getBooks(int pageIndex, int pageLimit) {
        int offset = pageLimit * (pageIndex -1);
        return jdbcTemplate.query(GET_BOOKS_SQL, bookRowMapper, pageLimit, offset);
    }

    public int addBook(Book book) {
        return jdbcTemplate.update(ADD_BOOK_SQL, book.getBookId(), book.getName(), book.getAuthor(),
                book.getPublish(), book.getISBN(), book.getIntroduction(), book.getLanguage(),
                book.getPressmark(), book.getState());
    }

    public int deleteBook(int bookId) {
        return jdbcTemplate.update(DELETE_BOOK_SQL, bookId);
    }

    public int reviseBookInfo(Book book) {
        return jdbcTemplate.update(REVISE_BOOK_INFO_SQL, book.getName(), book.getAuthor(),
                book.getPublish(), book.getISBN(), book.getIntroduction(), book.getLanguage(),
                book.getPressmark(), book.getState(), book.getBookId());
    }

    public List<Book> searchBooks(String searchWord, int pageIndex, int pageLimit) {
        String swcx = "%" + searchWord + "%";
        int offset = pageLimit * (pageIndex -1);
        return jdbcTemplate.query(SEARCH_BOOKS_SQL, bookRowMapper, swcx, swcx, pageLimit, offset);
    }
}
