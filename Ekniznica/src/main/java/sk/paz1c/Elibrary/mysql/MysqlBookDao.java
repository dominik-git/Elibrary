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
import sk.paz1c.Elibrary.model.Category;

public class MysqlBookDao implements BookDao {

	private JdbcTemplate jdbcTemplate;

	public MysqlBookDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;

	}

	@Override
	public Book getBookById(long id) {
		String sql = "SELECT Book.id as id, Book.name as name, Book.author as author, Book.description as description, Book.year_of_publication as year_of_publication, Category.id categoryId"
				+ " FROM Book " + "inner join Category " + "on Book.category_id = Category.id " + "where Book.id = "
				+ id + ";";
		try {
			Book book = jdbcTemplate.queryForObject(sql, new RowMapper<Book>() {

				@Override
				public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
					Book book = new Book();
					Category category = DaoFactory.INSTANCE.getCategoryDao().getCategoryById(rs.getLong("categoryId"));
					book.setCategory(category);
					book.setId(rs.getLong("id"));
					book.setName(rs.getString("name"));
					book.setAuthor(rs.getString("author"));
					book.setDescription(rs.getString("description"));
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
		String sql = "SELECT Book.id as id, Book.name as name, Book.author as author, Book.description as description, Book.year_of_publication as year_of_publication,Category.id categoryId"
				+ " FROM Book " + "inner join Category " + "on Book.category_id = Category.id " + "where Book.name = '"
				+ name + "';";
		try {
			Book book = jdbcTemplate.queryForObject(sql, new RowMapper<Book>() {

				@Override
				public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
					Book book = new Book();
					Category category = DaoFactory.INSTANCE.getCategoryDao().getCategoryById(rs.getLong("categoryId"));
					book.setCategory(category);
					book.setId(rs.getLong("id"));
					book.setName(rs.getString("name"));
					book.setAuthor(rs.getString("author"));
					book.setDescription(rs.getString("description"));
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
			values.put("category_id", book.getCategory().getId());
			long id = sjinsert.executeAndReturnKey(values).longValue();
			book.setId(id);
		}
		return book;
	}

	@Override
	public List<Book> getAllBooks() {
		String sql = "SELECT Book.id as id, Book.name as name, Book.author as author, Book.description as description, Book.year_of_publication as year_of_publication, Category.id categoryId"
				+ " FROM Book " + "inner join Category " + "on Book.category_id = Category.id; ";
//		ResultSetExtractor<Map<String, List<String>>>

		List<Book> result = jdbcTemplate.query(sql, new ResultSetExtractor<List<Book>>() {

			@SuppressWarnings("null")
			@Override
			public List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Book> result = new ArrayList<Book>();

				Book book = null;
				while (rs.next()) {
					book = new Book();
					Category category = DaoFactory.INSTANCE.getCategoryDao().getCategoryById(rs.getLong("categoryId"));
					book.setCategory(category);
					book.setId(rs.getLong("id"));
					book.setName(rs.getString("name"));
					book.setAuthor(rs.getString("author"));
					book.setDescription(rs.getString("description"));
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
		builder.append(
				" Book.description as description, Book.year_of_publication as year_of_publication, Category.id categoryId Category.name as catName");
		builder.append(" FROM Book inner join Category " + "on Book.category_id = Category.id ");
		builder.append(" where Book.name like ");
		builder.append("\"%" + name + "%\"; ");
		System.out.println(builder.toString());

		List<Book> result = jdbcTemplate.query(builder.toString(), new ResultSetExtractor<List<Book>>() {

			@SuppressWarnings("null")
			@Override
			public List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Book> result = new ArrayList<Book>();

				Book book = null;
				while (rs.next()) {
					book = new Book();
					Category category = DaoFactory.INSTANCE.getCategoryDao().getCategoryById(rs.getLong("categoryId"));
					book.setCategory(category);
					book.setId(rs.getLong("id"));
					book.setName(rs.getString("name"));
					book.setAuthor(rs.getString("author"));
					book.setDescription(rs.getString("description"));
					book.setYearOfPublication(rs.getDate("year_of_publication"));
					result.add(book);
				}
				return result;
			}

		});

		return result;
	}

	@Override
	public Long deleteBookById(long id) {
		String sql = "DELETE FROM Book WHERE Book.id = " + id + ";";

		jdbcTemplate.update(sql);

		return id;
	}

	@Override
	public Book updateBook(Book book) {
		jdbcTemplate.update(
				"UPDATE Book SET  name = ?  ,author = ? ,description = ? ,category_id= ? ,year_of_publication = ? WHERE id  = ?",
				book.getName(), book.getAuthor(), book.getDescription(), book.getCategory().getId(),
				book.getYearOfPublication(), book.getId());
		return book;
	}
}
