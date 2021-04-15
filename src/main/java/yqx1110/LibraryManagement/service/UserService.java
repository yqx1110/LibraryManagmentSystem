package yqx1110.LibraryManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import yqx1110.LibraryManagement.dao.AdminDao;
import yqx1110.LibraryManagement.dao.BookDao;
import yqx1110.LibraryManagement.dao.UserDao;
import yqx1110.LibraryManagement.entity.Book;
import yqx1110.LibraryManagement.entity.User;

import java.util.List;

public abstract class UserService<UserSpecificDao extends UserDao> {
    protected BookDao bookDao;
    protected UserSpecificDao userSpecificDao;

    @Autowired
    public final void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

//    abstract public String getName(int uuid);
//    abstract public int login(int uuid, String passwd);
//    abstract public int resetPasswd(int uuid, String passwd, String newPasswd);

    public int login(int uuid, String passwd) {
        if (passwd.equals(userSpecificDao.getPasswd(uuid))) {
            return 1;
        } else {
            return 0;
        }
    }

    public int resetPasswd(int uuid, String oldPasswd, String newPasswd) {
        if (login(uuid, oldPasswd) == 1) {
            if (userSpecificDao.updatePasswd(uuid, newPasswd) > 0) {
                return 1;
            } else {
                return -1;
            }
        }
        else {
            return 0;
        }
    }

    public Book getBookById(int bookId) {
        return bookDao.getBookById(bookId);
    }

    public List<Book> getBooks(int pageIndex, int pageLimit) {
        return bookDao.getBooks(pageIndex, pageLimit);
    }

    public List<Book> searchBooks(String searchWord, int pageIndex, int pageLimit) {
        return bookDao.searchBooks(searchWord, pageIndex, pageLimit);
    }

}
