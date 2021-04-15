package yqx1110.LibraryManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yqx1110.LibraryManagement.dao.BookDao;
import yqx1110.LibraryManagement.entity.Book;

import java.util.List;

@Component
public class BookService {
    private BookDao bookDao;

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public Book getBookById(int bookId) {
        return bookDao.getBookById(bookId);
    }

    public List<Book> getBooks(int pageIndex, int pageLimit) {
        return bookDao.getBooks(pageIndex, pageLimit);
    }

    public boolean addBook(Book book) {
        return bookDao.addBook(book) > 0;
    }

    public boolean deleteBook(int bookId) {
        return bookDao.deleteBook(bookId) > 0;
    }

    public boolean reviseBookInfo(Book book) {
        return bookDao.reviseBookInfo(book) > 0;
    }

    public List<Book> searchBooks(String searchWord, int pageIndex, int pageLimit) {
        return bookDao.searchBooks(searchWord, pageIndex, pageLimit);
    }
}
