package yqx1110.LibraryManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yqx1110.LibraryManagement.dao.AdminDao;
import yqx1110.LibraryManagement.dao.ReaderDao;
import yqx1110.LibraryManagement.entity.Admin;
import yqx1110.LibraryManagement.entity.Book;
import yqx1110.LibraryManagement.entity.Reader;
import yqx1110.LibraryManagement.entity.User;

@Component
public class AdminService extends UserService<AdminDao> {
    //private AdminDao adminDao;
    private ReaderDao readerDao;

    @Autowired
    public void setAdminDao(AdminDao adminDao) {
        this.userSpecificDao = adminDao;
    }

    public Admin getAdminById(int uuid) {
        return userSpecificDao.getById(uuid);
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

    public boolean addReader(Reader reader) {
        return readerDao.addReader(reader) > 0;
    }

    public boolean deleteReader(int uuid) {
        return readerDao.deleteReaderById(uuid) > 0;
    }

    public boolean reviseReaderInfo(Reader reader) {
        return readerDao.updateReaderInfo(reader) > 0;
    }

}
