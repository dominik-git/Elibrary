package sk.paz1c.Elibrary.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import sk.paz1c.Elibrary.interfaces.ReaderDao;
import sk.paz1c.Elibrary.model.Book;
import sk.paz1c.Elibrary.model.Reader;

public class MysqlReaderDao implements ReaderDao {

	private JdbcTemplate jdbcTemplate;

	public MysqlReaderDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Reader getReaderByUsernameAndPassword(String username, String password) {

		String sql = "SELECT * FROM Reader where username = ? and password = ? ;";

		try {
			Reader reader = jdbcTemplate.queryForObject(sql, new Object[] { username, password },
					new RowMapper<Reader>() {

						public Reader mapRow(ResultSet rs, int rowNum) throws SQLException {

							Reader reader = new Reader();
							reader.setName(rs.getString("name"));
							reader.setId(rs.getLong("id"));
							reader.setSurname(rs.getString("surname"));
							reader.setUsername(rs.getString("username"));
							reader.setPassword(rs.getString("password"));
							reader.setAdmin(rs.getInt("isAdmin"));
							reader.setBirthDate(rs.getDate("date_of_birth"));
							reader.setGender(rs.getString("gender"));
							reader.setEmail(rs.getString("email"));
							reader.setGender(rs.getString("gender"));
							reader.setAdmin(rs.getInt("isAdmin"));
							return reader;
						}
					});
			return reader;

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	@Override
	public Reader saveReader(Reader reader) {
		if (reader == null || reader.getName() == null || reader.getSurname() == null || reader.getUsername() == null || reader.getBirthDate() == null || reader.getGender() == null)
			return null;
		if (reader.getId() == null) {
			// INSERT
			SimpleJdbcInsert sjinsert = new SimpleJdbcInsert(jdbcTemplate);
			sjinsert.withTableName("Reader");
			sjinsert.usingColumns("name", "surname", "username", "password", "isAdmin", "date_of_birth", "gender",
					"email");
			sjinsert.usingGeneratedKeyColumns("id");

			Map<String, Object> values = new HashMap<String, Object>();
			values.put("name", reader.getName());
			values.put("surname", reader.getSurname());
			values.put("username", reader.getUsername());
			values.put("password", reader.getPassword());
			values.put("isAdmin", reader.getAdmin());
			values.put("date_of_birth", reader.getBirthDate());
			values.put("gender", reader.getGender());
			values.put("email", reader.getEmail());

			long id = sjinsert.executeAndReturnKey(values).longValue();
			reader.setId(id);
		}
		
		return reader;

	}

	@Override
	public List<Reader> getAllReaders() {
		String sql = "SELECT Reader.id as id, Reader.name as name, Reader.surname as surname,"
				+ " Reader.username as username, Reader.password as password,"
				+ " Reader.isAdmin as isAdmin, Reader.date_of_birth as date_of_birth, " + "Reader.gender as gender"
				+ " FROM Reader where isAdmin=0;";
		
		List<Reader> result = jdbcTemplate.query(sql, new ResultSetExtractor<List<Reader>>() {

			@SuppressWarnings("null")
			@Override
			public List<Reader> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Reader> result = new ArrayList<Reader>();

				Reader reader = null;
				while (rs.next()) {
					reader = new Reader();
					reader.setId(rs.getLong("id"));
					reader.setName(rs.getString("name"));
					reader.setUsername(rs.getString("username"));
					reader.setSurname(rs.getString("surname"));
					reader.setPassword(rs.getString("password"));
					reader.setGender(rs.getString("gender"));
					if (rs.getBoolean("isAdmin")) {
						reader.setAdmin(1);
					} else {
						reader.setAdmin(0);
					}
					reader.setBirthDate(rs.getDate("date_of_birth"));

					result.add(reader);
				}
				return result;
			}

		});

		return result;
	}

	@Override
	public List<Reader> getAllReadersByFullName(String string) {

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT Reader.id as id, Reader.name as name, Reader.surname as surname,");
		builder.append(" Reader.username as username, Reader.password as password,");
		builder.append(
				" Reader.isAdmin as isAdmin, Reader.date_of_birth as date_of_birth, " + "Reader.gender as gender");
		builder.append(" FROM Reader where isAdmin = 0 and CONCAT(name, ' ',surname) like ");
		builder.append("\"%" + string + "%\"; ");

		List<Reader> result = jdbcTemplate.query(builder.toString(), new ResultSetExtractor<List<Reader>>() {

			@SuppressWarnings("null")
			@Override
			public List<Reader> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Reader> result = new ArrayList<Reader>();

				Reader reader = null;
				while (rs.next()) {
					reader = new Reader();
					reader.setId(rs.getLong("id"));
					reader.setName(rs.getString("name"));
					reader.setUsername(rs.getString("username"));
					reader.setSurname(rs.getString("surname"));
					reader.setPassword(rs.getString("password"));
					reader.setGender(rs.getString("gender"));
					if (rs.getBoolean("isAdmin")) {
						reader.setAdmin(1);
					} else {
						reader.setAdmin(0);
					}
					reader.setBirthDate(rs.getDate("date_of_birth"));

					result.add(reader);
				}
				return result;
			}

		});

		return result;
	}

	@Override
	public Reader getReaderByName(String name) {
		String sql = "SELECT * FROM Reader where name = ? ;";

		try {
			Reader reader = jdbcTemplate.queryForObject(sql, new Object[] { name }, new RowMapper<Reader>() {

				public Reader mapRow(ResultSet rs, int rowNum) throws SQLException {

					Reader reader = new Reader();
					reader.setName(rs.getString("name"));
					reader.setId(rs.getLong("id"));
					reader.setSurname(rs.getString("surname"));
					reader.setUsername(rs.getString("username"));
					reader.setPassword(rs.getString("password"));
					reader.setAdmin(rs.getInt("isAdmin"));
					reader.setBirthDate(rs.getDate("date_of_birth"));
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
	public Reader getReaderById(Long id) {
		String sql = "SELECT * FROM eLibrary.Reader where id = ? ;";

		try {
			Reader reader = jdbcTemplate.queryForObject(sql, new Object[] { id }, new RowMapper<Reader>() {

				public Reader mapRow(ResultSet rs, int rowNum) throws SQLException {
					Reader reader = new Reader();
					reader.setName(rs.getString("name"));
					reader.setId(rs.getLong("id"));
					reader.setSurname(rs.getString("surname"));
					reader.setUsername(rs.getString("username"));
					reader.setPassword(rs.getString("password"));
					reader.setAdmin(rs.getInt("isAdmin"));
					reader.setBirthDate(rs.getDate("date_of_birth"));
					reader.setGender(rs.getString("gender"));
					reader.setEmail(rs.getString("email"));
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
	public Long deleteReaderById(long id) {
		String sql = "DELETE FROM Reader WHERE reader.id = " + id + ";";
		jdbcTemplate.update(sql);

		return id;
	}

	@Override
	public Reader updateReader(Reader reader) {

		jdbcTemplate.update(
				"UPDATE Reader SET  password = ? ,name = ? ,surname= ? ,username = ? ,date_of_birth = ? ,gender = ? ,email = ? WHERE Reader.id  = ? ; ",
				reader.getPassword(), reader.getName(), reader.getSurname(), reader.getUsername(),
				reader.getBirthDate(), reader.getGender(), reader.getEmail(), reader.getId());
		return reader;
	}

	@Override
	public void changePasswordById(long id, String password) {
		jdbcTemplate.update("UPDATE Reader SET password = ?  WHERE Reader.id  = ? ; ", password, id);

	} 

	
}
