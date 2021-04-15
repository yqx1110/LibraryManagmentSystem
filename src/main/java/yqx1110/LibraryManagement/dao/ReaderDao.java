package yqx1110.LibraryManagement.dao;

import yqx1110.LibraryManagement.entity.Reader;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ReaderDao extends UserDao{
    private JdbcTemplate jdbcTemplate;
    private final static RowMapper<Reader> readerInfoRowMapper = (resultSet, i) -> new Reader(
            resultSet.getInt("reader_id"),
            resultSet.getString("name"),
            resultSet.getString("sex"),
            resultSet.getDate("birth")
    );

    @Autowired
    public void setJdbcTemplate(JdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    private static final String GET_READER_SQL = "select * from reader_info where reader_id = ?";
    private static final String GET_ALL_READERS_SQL = "select * from reader_info";
    private static final String DELETE_READER_SQL = "delete from reader_info where reader_id = ?";
    private static final String ADD_READER_SQL =  "insert into reader_info values(?,?,?,?,?)";
    private static final String UPDATE_READER_SQL = "update reader_info set name=?, sex=?, birth=? where reader_id = ?";
    private static final String GET_READER_NAME_SQL = "select name from reader_info where reader_id = ?";
    private static final String GET_READER_PASSWD_SQL = "select password from reader_info where reader_id = ?";
    private static final String RESET_READER_PASSWD = "update reader_info set password = ? where reader_id = ?";

//    public String getName(int uuid) {
//        return jdbcTemplate.queryForObject(GET_READER_NAME_SQL, String.class, uuid);
//    }

    @Override
    public Reader getById(int uuid) {
        return jdbcTemplate.queryForObject(GET_READER_SQL, readerInfoRowMapper, uuid);
    }

    @Override
    public String getPasswd(int uuid) {
        return jdbcTemplate.queryForObject(GET_READER_PASSWD_SQL, String.class, uuid);
    }

    @Override
    public int updatePasswd(int uuid, String passwd) {
        return jdbcTemplate.update(RESET_READER_PASSWD, passwd, uuid);
    }

    public List<Reader> getAllReaders() {
        return jdbcTemplate.query(GET_ALL_READERS_SQL, readerInfoRowMapper);
    }

    public int deleteReaderById(int uuid) {
        return jdbcTemplate.update(DELETE_READER_SQL, uuid);
    }

    public int addReader(Reader reader) {
        return jdbcTemplate.update(ADD_READER_SQL, reader.getUuid(),
                reader.getName(), reader.getSex(), reader.getDateOfBirth(),reader.getUuid());
    }

    public int updateReaderInfo(Reader reader) {
        int stuId = reader.getUuid();
        String name = reader.getName();
        String sex = reader.getSex();
        Date dateOfBirth = reader.getDateOfBirth();
        return jdbcTemplate.update(UPDATE_READER_SQL, name, sex, dateOfBirth);
    }
}
