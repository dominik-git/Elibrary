package sk.paz1c.Elibrary.exactor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import sk.paz1c.Elibrary.model.Reader;

@SuppressWarnings("rawtypes")
public class ReaderRowMapper implements RowMapper {

	@Override
	public Reader mapRow(ResultSet rs, int rowNum) {
		
		Reader reader = new Reader();
		try {
			reader.setName(rs.getString("name"));
			reader.setId(rs.getLong("id"));
			reader.setSurname(rs.getString("surname"));
			reader.setUsername(rs.getString("username"));
			reader.setAdmin(rs.getInt("isAdmin"));
		} catch (IncorrectResultSizeDataAccessException ex) {
			if (ex.getActualSize() == 0) {
				System.out.println("user not found2");
	        }
			// TODO Auto-generated catch block	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("user not found");
			e.printStackTrace();
		}
		

		return reader;
	}

}
