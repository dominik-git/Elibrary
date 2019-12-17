package sk.paz1c.Elibrary.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
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
	public Book addBook(Book book) {
		if (book == null)
			return null;
		if (book.getId() == null) {
			// INSERT
			SimpleJdbcInsert sjinsert = new SimpleJdbcInsert(jdbcTemplate);
			sjinsert.withTableName("Book");
			sjinsert.usingColumns("name", "author", "description", "category_id", "year_of_publication");
			sjinsert.usingGeneratedKeyColumns("id");

			Map<String, Object> values = new HashMap<String, Object>();
			values.put("name", book.getName());
			values.put("author", book.getAuthor());
			values.put("description", book.getDescription());
			values.put("year_of_publication", book.getYearOfPublication());
			int categoryId = Integer.parseInt(book.getCategory());
			values.put("category_id", categoryId);
			long id = sjinsert.executeAndReturnKey(values).longValue();
			book.setId(id);
		}
		return book;
	}


	@Override
	public List<Book> getAllBooks() {
		String sql = "SELECT Book.id as id, Book.name as name, Book.author as author, Book.description as description, Book.year_of_publication as year_of_publication, Category.name as category"
				+ " FROM Book " + "inner join Category " + 
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

		@Override
		public List<Book> getAllBooksByName(String name) {
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT Book.id as id, Book.name as name, Book.author as author,");
			builder.append(" Book.description as description, Book.year_of_publication as year_of_publication, Category.name as category");
			builder.append(" FROM Book inner join Category " + "on Book.category_id = Category.id ");
			builder.append(" where Book.name like ");
			builder.append("\"%"+name+"%\"; ");
			System.out.println(builder.toString());
			
//			String sql = "SELECT Reader.id as id, Reader.name as name, Reader.surname as surname,"
//					+ " Reader.username as username, Reader.password as password,"
//					+ " Reader.isAdmin as isAdmin, Reader.date_of_birth as date_of_birth, " + "Reader.gender as gender"
//					+ " FROM Reader where isAdmin = 0 and (name like " + name +"%\" " + "or surname like "+ surname+"%\" )";


			List<Book> result = jdbcTemplate.query(builder.toString(), new ResultSetExtractor<List<Book>>() {

				@SuppressWarnings("null")
				@Override
				public List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
					List<Book> result = new ArrayList<Book>();

					Book book = null;
					while (rs.next()) {
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
