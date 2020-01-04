package sk.paz1c.Elibrary.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import sk.paz1c.Elibrary.interfaces.RentedBookDao;
import sk.paz1c.Elibrary.model.Book;
import sk.paz1c.Elibrary.model.Category;
import sk.paz1c.Elibrary.model.Reader;
import sk.paz1c.Elibrary.model.RentedBook;

public class MysqlRentedBookDao implements RentedBookDao {

	private JdbcTemplate jdbcTemplate;

	public MysqlRentedBookDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// get rented books by reader id
	@Override
	public List<RentedBook> getRentedBookById(long id) {
		String sql = "SELECT Rented_book.id_book AS bookId ,Rented_book.id_reader AS readerId, Rented_book.is_returned AS isReturned, "
				+ "    Rented_book.id AS id, Rented_book.date_of_rent as date_of_rent, Rented_book.deadline as deadline,Rented_book.date_of_return as date_of_return "
				+ "FROM Rented_book INNER JOIN Book ON Rented_book.id_book = Book.id INNER JOIN Reader ON Rented_book.id_reader = Reader.id INNER JOIN Category ON Book.category_id = Category.id "
				+ "where reader.id =" + id + ";";

		List<RentedBook> result = jdbcTemplate.query(sql, new ResultSetExtractor<List<RentedBook>>() {

			@SuppressWarnings("null")
			@Override
			public List<RentedBook> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<RentedBook> result = new ArrayList<RentedBook>();

				RentedBook rentedBook = null;
				Book book = null;
				Reader reader = null;
				while (rs.next()) {
					rentedBook = new RentedBook();

					book = DaoFactory.INSTANCE.getBookDao().getBookById(rs.getLong("bookId"));
					rentedBook.setBook(book);

					reader = DaoFactory.INSTANCE.getReaderDao().getReaderById(rs.getLong("readerId"));

					rentedBook.setReader(reader);
					rentedBook.setId(rs.getLong("id"));
					rentedBook.setIsReturned(rs.getInt("isReturned"));
					rentedBook.setDateOfRent(rs.getDate("date_of_rent"));
					rentedBook.setDeadline(rs.getDate("deadline"));
					rentedBook.setDateOfReturn(rs.getDate("date_of_return"));

					result.add(rentedBook);
				}
				return result;
			}

		});

		return result;
	}

	@Override
	public List<RentedBook> getNonReturnedRentedBookByReaderId(long id) {
		String sql = "SELECT Rented_book.id_book AS bookId,Rented_book.id_reader as readerId, Rented_book.is_returned AS isReturned, "
				+ "    Rented_book.id AS id, Rented_book.date_of_rent as date_of_rent, Rented_book.deadline as deadline,Rented_book.date_of_return as date_of_return "
				+ "FROM Rented_book INNER JOIN Book ON Rented_book.id_book = Book.id INNER JOIN Reader ON Rented_book.id_reader = Reader.id INNER JOIN Category ON Book.category_id = Category.id "
				+ "where reader.id =" + id + " and date_of_return is null;";

		List<RentedBook> result = jdbcTemplate.query(sql, new ResultSetExtractor<List<RentedBook>>() {

			@SuppressWarnings("null")
			@Override
			public List<RentedBook> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<RentedBook> result = new ArrayList<RentedBook>();

				RentedBook rentedBook = null;
				Book book = null;
				Reader reader = null;

				while (rs.next()) {
					rentedBook = new RentedBook();

					book = DaoFactory.INSTANCE.getBookDao().getBookById(rs.getLong("bookId"));
					rentedBook.setBook(book);

					reader = DaoFactory.INSTANCE.getReaderDao().getReaderById(rs.getLong("readerId"));

					rentedBook.setReader(reader);
					rentedBook.setId(rs.getLong("id"));
					rentedBook.setIsReturned(rs.getInt("isReturned"));
					rentedBook.setDateOfRent(rs.getDate("date_of_rent"));
					rentedBook.setDeadline(rs.getDate("deadline"));
					rentedBook.setDateOfReturn(rs.getDate("date_of_return"));

					result.add(rentedBook);

				}
				return result;
			}

		});

		return result;
	}

	@Override
	public List<RentedBook> getAllRentedBooksForAdmin() {
		String sql = "SELECT Rented_book.id_book AS bookId, Rented_book.id_reader as readerId, Rented_book.is_returned AS isReturned, "
				+ " Rented_book.id AS id, Rented_book.date_of_rent as date_of_rent, Rented_book.deadline as deadline,Rented_book.date_of_return as date_of_return "
				+ "FROM Rented_book "
				+ "WHERE Rented_book.is_returned= '0' ; ";
		List<RentedBook> result = jdbcTemplate.query(sql, new ResultSetExtractor<List<RentedBook>>() {

			@SuppressWarnings("null")
			@Override
			public List<RentedBook> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<RentedBook> result = new ArrayList<RentedBook>();

				RentedBook rentedBook = null;
				Book book = null;
				Reader reader = null;
				while (rs.next()) {
					rentedBook = new RentedBook();
					book = DaoFactory.INSTANCE.getBookDao().getBookById(rs.getLong("bookId"));				
					rentedBook.setBook(book);

					reader = DaoFactory.INSTANCE.getReaderDao().getReaderById(rs.getLong("readerId"));
					rentedBook.setReader(reader);
					

					rentedBook.setId(rs.getLong("id"));
					rentedBook.setIsReturned(rs.getInt("isReturned"));
					rentedBook.setDateOfRent(rs.getDate("date_of_rent"));
					rentedBook.setDeadline(rs.getDate("deadline"));
					rentedBook.setDateOfReturn(rs.getDate("date_of_return"));
System.out.println(rentedBook);
					result.add(rentedBook);
				}
				return result;
			}

		});
		return result;
	}

	@Override
	public RentedBook returnBook(RentedBook rentedBook) {
		String sql = "UPDATE Rented_book " + "SET  is_returned = 1,date_of_return = current_date() "
				+ "WHERE Rented_book.id  =  " + rentedBook.getId();
		jdbcTemplate.update(sql);

		return rentedBook;
	}

	@Override
	public void addRentedBook(Long userId, Long bookId) {
		String sql = "INSERT INTO `elibrary`.`rented_book` (`id_reader`, `id_book`, `date_of_rent`, `deadline`, is_returned) VALUES ('"
				+ userId + "', '" + bookId + "', " + "NOW()" + ", " + "NOW() + INTERVAL 1 MONTH" + ",0);";

		jdbcTemplate.update(sql);
	}

	@Override
	public List<RentedBook> getAlReturnedBooksForAdmin() {
		String sql = "SELECT Rented_book.id_book AS bookId,Rented_book.id_reader as readerId, Rented_book.is_returned AS isReturned, "
				+ "    Rented_book.id AS id, Rented_book.date_of_rent as date_of_rent, Rented_book.deadline as deadline,Rented_book.date_of_return as date_of_return "
				+ "FROM Rented_book INNER JOIN Book ON Rented_book.id_book = Book.id INNER JOIN Reader ON Rented_book.id_reader = Reader.id INNER JOIN Category ON Book.category_id = Category.id "
				+ "WHERE Rented_book.is_returned= '1' ; ";
		List<RentedBook> result = jdbcTemplate.query(sql, new ResultSetExtractor<List<RentedBook>>() {

			@SuppressWarnings("null")
			@Override
			public List<RentedBook> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<RentedBook> result = new ArrayList<RentedBook>();

				RentedBook rentedBook = null;
				Book book = null;
				Reader reader = null;
				while (rs.next()) {
					rentedBook = new RentedBook();

					book = DaoFactory.INSTANCE.getBookDao().getBookById(rs.getLong("bookId"));
					rentedBook.setBook(book);

					reader = DaoFactory.INSTANCE.getReaderDao().getReaderById(rs.getLong("readerId"));

					rentedBook.setReader(reader);
					rentedBook.setId(rs.getLong("id"));
					rentedBook.setIsReturned(rs.getInt("isReturned"));
					
					rentedBook.setDateOfRent(rs.getDate("date_of_rent"));
					rentedBook.setDeadline(rs.getDate("deadline"));
					rentedBook.setDateOfReturn(rs.getDate("date_of_return"));

					result.add(rentedBook);
				}
				return result;
			}

		});
		return result;
	}

}
