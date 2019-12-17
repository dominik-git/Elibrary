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
	public Reader saveReader(Reader reader) {
		// String sql ="INSERT INTO Reader (`name`, `surname`, `username`, `password`,
		// `isAdmin`, `date_of_birth`, `gender`, `email`) VALUES (?, ?, ?, ?, ?, ?,
		// '?',?');";

		if (reader == null)
			return null;
		if (reader.getId() == null) {
			// INSERT
			SimpleJdbcInsert sjinsert = new SimpleJdbcInsert(jdbcTemplate);
			sjinsert.withTableName("Reader");
			sjinsert.usingColumns("name", "surname", "username", "password", "isAdmin", "date_of_birth", "gender","email");
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
//		} else {
//			//UPDATE
//			String sql = "UPDATE subject SET name = ? WHERE id = " + subject.getId();
//			jdbcTemplate.update(sql, subject.getName());
//			String deleteSql = "DELETE FROM student_at_subject WHERE subject_id = "
//								+ subject.getId();
//			jdbcTemplate.update(deleteSql);
//			insertStudents(subject);
//		}
		return reader;

	}


	@Override
	public List<Reader> getAllReaders() {
		String sql = "SELECT Reader.id as id, Reader.name as name, Reader.surname as surname,"
				+ " Reader.username as username, Reader.password as password,"
				+ " Reader.isAdmin as isAdmin, Reader.date_of_birth as date_of_birth, " + "Reader.gender as gender"
				+ " FROM Reader where isAdmin=0;";
//		ResultSetExtractor<Map<String, List<String>>>

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
//		String[] parts = string.split(" ");
//		String name = "";
//		String surname = "";
//		if (parts.length >= 1) {
//			name = parts[0];
//		}
//		if (parts.length >= 2) {
//			surname = parts[1];
//		}
		
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT Reader.id as id, Reader.name as name, Reader.surname as surname,");
		builder.append(" Reader.username as username, Reader.password as password,");
		builder.append(" Reader.isAdmin as isAdmin, Reader.date_of_birth as date_of_birth, " + "Reader.gender as gender");
		builder.append(" FROM Reader where isAdmin = 0 and CONCAT(name, ' ',surname) like ");
		builder.append("\"%"+string+"%\"; ");
		System.out.println(builder.toString());
		
//		String sql = "SELECT Reader.id as id, Reader.name as name, Reader.surname as surname,"
//				+ " Reader.username as username, Reader.password as password,"
//				+ " Reader.isAdmin as isAdmin, Reader.date_of_birth as date_of_birth, " + "Reader.gender as gender"
//				+ " FROM Reader where isAdmin = 0 and (name like " + name +"%\" " + "or surname like "+ surname+"%\" )";


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
}
