package sk.paz1c.Elibrary.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import sk.paz1c.Elibrary.interfaces.RentedBookDao;
import sk.paz1c.Elibrary.model.Book;

public class MysqlRentedBookDao implements RentedBookDao {
	
	private JdbcTemplate jdbcTemplate;

	public MysqlRentedBookDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public List<Book> getRentedBookById(long id) {
		String sql = "select Book.name as name ,Book.author as author, Book.description, Category.name as category " + 
				"from Rented_book " + 
				"inner join Book " + 
				"on Rented_book.id_book = Book.id " + 
				"inner join Reader " + 
				"on Rented_book.id_reader = Reader.id " + 
				"inner join Category " + 
				"on Book.category_id = Category.id " + 
				"where reader.id = 1;";
		
//		ResultSetExtractor<Map<String, List<String>>>
		
		List<Book> result = jdbcTemplate.query(sql, new ResultSetExtractor<List<Book>>() {

			@SuppressWarnings("null")
			@Override
			public List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Book> result = new ArrayList<Book>();
			
				Book book = null;
				while(rs.next()) {
					book = new Book();
					book.setName(rs.getString("name"));
					book.setAuthor(rs.getString("author"));
					book.setCategory(rs.getString("category"));		
					result.add(book);
			}
				return result;
			}
			
		});
		
		return result;
	}

}
