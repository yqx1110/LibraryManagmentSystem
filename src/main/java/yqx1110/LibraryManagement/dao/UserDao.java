package yqx1110.LibraryManagement.dao;

import yqx1110.LibraryManagement.entity.User;

abstract public class UserDao {
    abstract public User getById(int uuid);

    abstract public String getPasswd(int uuid);

    abstract public int updatePasswd(int uuid, String passwd);
}
