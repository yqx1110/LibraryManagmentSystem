package yqx1110.LibraryManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yqx1110.LibraryManagement.dao.ReaderDao;
import yqx1110.LibraryManagement.entity.Reader;

@Component
public class ReaderService extends UserService<ReaderDao> {

    @Autowired
    public void setReaderDao(ReaderDao readerDao) {
        this.userSpecificDao = readerDao;
    }

    public Reader getReaderById(int uuid) {
        return this.userSpecificDao.getById(uuid);
    }

    public boolean updateReaderInfo(Reader reader) {
        return this.userSpecificDao.updateReaderInfo(reader) > 0;
    }
}
