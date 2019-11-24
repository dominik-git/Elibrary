package sk.paz1c.Elibrary.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import sk.paz1c.Elibrary.exactor.ReaderRowMapper;
import sk.paz1c.Elibrary.interfaces.ReaderDao;
import sk.paz1c.Elibrary.model.Reader;

public class MysqlReaderDao implements ReaderDao {

	private JdbcTemplate jdbcTemplate;

	public MysqlReaderDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Reader getReaderById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reader getReaderByUsernameAndPassword(String username, String password) {

		String sql = "SELECT * FROM Reader where username like ? and password like ? ;";
		
		try {
			Reader reader = jdbcTemplate.queryForObject(sql, new Object[] { username, password },new RowMapper<Reader>() {

						public Reader mapRow(ResultSet rs, int rowNum) throws SQLException {

							Reader reader = new Reader();
							reader.setName(rs.getString("name"));
							reader.setId(rs.getLong("id"));
							reader.setSurname(rs.getString("surname"));
							reader.setUsername(rs.getString("username"));
							reader.setAdmin(rs.getInt("isAdmin"));
							Timestamp timestamp = rs.getTimestamp("date_of_birth");
							if (timestamp != null)
								reader.setBirthDate(timestamp.toLocalDateTime());
							reader.setGender(rs.getString("gender"));

							return reader;
						}
					});
			return reader;

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	@Override
	public Reader getReaderByUserName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveReader(Reader reader) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUserById(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Reader> getAllReaders() {
		// TODO Auto-generated method stub
		return null;
	}


}
