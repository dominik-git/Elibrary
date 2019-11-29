package sk.paz1c.Elibrary.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import sk.paz1c.Elibrary.interfaces.BookDao;
import sk.paz1c.Elibrary.model.Book;

public class MysqlBookDao implements BookDao {

	private JdbcTemplate jdbcTemplate;

	public MysqlBookDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;

	}

	@Override
	public Book getBookById(long id) {
		String sql = "SELECT Book.id as id, Book.name as name, Book.author as author, Book.description as description, Book.year_of_publication as year_of_publication, Category.name as category"
				+ " FROM Book " + 
				"inner join Category " + 
				"on Book.category_id = Category.id " 
				+ "where Book.id = " + id + ";";
		try {
			Book book = jdbcTemplate.queryForObject(sql, new RowMapper<Book>() {

				@Override
				public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
					Book book = new Book();
			    	book.setId(rs.getLong("id"));
					book.setName(rs.getString("name"));
					book.setAuthor(rs.getString("author"));
					book.setDescription(rs.getString("description"));
					book.setCategory(rs.getString("category"));
					book.setYearOfPublication(rs.getDate("year_of_publication"));
					return book;
				}

			});
			return book;

		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Book getBookByName(String name) {
		String sql = "SELECT Book.id as id, Book.name as name, Book.author as author, Book.description as description, Book.year_of_publication as year_of_publication, Category.name as category"
				+ " FROM Book " + 
				"inner join Category " + 
				"on Book.category_id = Category.id " 
				+ "where Book.name = '" + name + "';";
		try {
			Book book = jdbcTemplate.queryForObject(sql, new RowMapper<Book>() {

				@Override
				public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
					Book book = new Book();
			    	book.setId(rs.getLong("id"));
					book.setName(rs.getString("name"));
					book.setAuthor(rs.getString("author"));
					book.setDescription(rs.getString("description"));
					book.setCategory(rs.getString("category"));
					book.setYearOfPublication(rs.getDate("year_of_publication"));
					return book;
				}

			});
			return book;

		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}


	@Override
	public Book getBookByYear(int year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBooksByCategoryId(long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteBookById(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Book> getAllBooks() {
		String sql = "SELECT Book.id as id, Book.name as name, Book.author as author, Book.description as description, Book.year_of_publication as year_of_publication, Category.name as category"
				+ " FROM Book " + 
				"inner join Category " + 
				"on Book.category_id = Category.id; ";
//		ResultSetExtractor<Map<String, List<String>>>
		
		List<Book> result = jdbcTemplate.query(sql, new ResultSetExtractor<List<Book>>() {

			@SuppressWarnings("null")
			@Override
			public List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Book> result = new ArrayList<Book>();
			
				Book book = null;
				while(rs.next()) {
					book = new Book();
					book.setId(rs.getLong("id"));
					book.setName(rs.getString("name"));
					book.setAuthor(rs.getString("author"));
					book.setDescription(rs.getString("description"));
					book.setCategory(rs.getString("category"));
					book.setYearOfPublication(rs.getDate("year_of_publication"));	
					result.add(book);
			}
				return result;
			}
			
		});
		
		return result;
	}

}
