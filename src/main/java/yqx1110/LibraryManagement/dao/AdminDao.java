package yqx1110.LibraryManagement.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import yqx1110.LibraryManagement.entity.Admin;
import yqx1110.LibraryManagement.entity.User;

@Component
public class AdminDao extends UserDao{
    private JdbcTemplate jdbcTemplate;
    private final RowMapper<Admin> adminRowMapper = ((resultSet, i) -> new Admin(
            resultSet.getInt("admin_id"),
            resultSet.getString("name")
    ));

    @Autowired
    public void setJdbcTemplate(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    private static final String GET_ADMIN_SQL = "select * from admin where admin_id = ?";
    private static final String GET_PASSWD_SQL = "select password from admin where admin_id = ?";
    private static final String UPDATE_PASSWD_SQL = "update admin set password = ? where admin_id = ?";

    @Override
    public Admin getById(int uuid) {
        return jdbcTemplate.queryForObject(GET_ADMIN_SQL, adminRowMapper, uuid);
    }

    @Override
    public String getPasswd(int uuid) {
        return jdbcTemplate.queryForObject(GET_PASSWD_SQL, String.class, uuid);
    }

    @Override
    public int updatePasswd(int uuid, String passwd) {
        return jdbcTemplate.update(UPDATE_PASSWD_SQL, passwd, uuid);
    }

}
