package sk.paz1c.Elibrary.mysql;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
import sk.paz1c.Elibrary.model.Reader;
import sk.paz1c.Elibrary.model.RentedBook;

public class MysqlRentedBookDao implements RentedBookDao {

	private JdbcTemplate jdbcTemplate;

	public MysqlRentedBookDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<RentedBook> getRentedBookById(long id) {
		String sql = "SELECT Book.name AS name, Category.name AS category, CONCAT(Reader.name, ' ', Reader.surname) AS fullName, Rented_book.is_returned AS isReturned, "
				+ "    Rented_book.id AS id, Rented_book.date_of_rent as date_of_rent, Rented_book.deadline as deadline,Rented_book.date_of_return as date_of_return "
				+ "FROM Rented_book INNER JOIN Book ON Rented_book.id_book = Book.id INNER JOIN Reader ON Rented_book.id_reader = Reader.id INNER JOIN Category ON Book.category_id = Category.id "
				+ "where reader.id =" + id +";";

		List<RentedBook> result = jdbcTemplate.query(sql, new ResultSetExtractor<List<RentedBook>>() {

			@SuppressWarnings("null")
			@Override
			public List<RentedBook> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<RentedBook> result = new ArrayList<RentedBook>();

				RentedBook book = null;
				while (rs.next()) {
					book = new RentedBook();
					book.setName(rs.getString("name"));
					book.setCategory(rs.getString("category"));
					book.setReaderFullName(rs.getString("fullName"));
					book.setId(rs.getLong("id"));
					book.setReturned(rs.getLong("isReturned"));
					book.setDateOfRent(rs.getDate("date_of_rent"));
					book.setDeadline(rs.getDate("deadline"));
					book.setDateOfReturn(rs.getDate("date_of_return"));
					
					result.add(book);
				}
				return result;
			}

		});

		return result;
	}
	
	@Override
	public List<RentedBook> getNonReturnedRentedBookById(long id) {
		String sql = "SELECT Book.name AS name, Category.name AS category, CONCAT(Reader.name, ' ', Reader.surname) AS fullName, Rented_book.is_returned AS isReturned, "
				+ "    Rented_book.id AS id, Rented_book.date_of_rent as date_of_rent, Rented_book.deadline as deadline,Rented_book.date_of_return as date_of_return "
				+ "FROM Rented_book INNER JOIN Book ON Rented_book.id_book = Book.id INNER JOIN Reader ON Rented_book.id_reader = Reader.id INNER JOIN Category ON Book.category_id = Category.id "
				+ "where reader.id =" + id +" and date_of_return is null;";

		List<RentedBook> result = jdbcTemplate.query(sql, new ResultSetExtractor<List<RentedBook>>() {

			@SuppressWarnings("null")
			@Override
			public List<RentedBook> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<RentedBook> result = new ArrayList<RentedBook>();

				RentedBook book = null;
				while (rs.next()) {
					book = new RentedBook();
					book.setName(rs.getString("name"));
					book.setCategory(rs.getString("category"));
					book.setReaderFullName(rs.getString("fullName"));
					book.setId(rs.getLong("id"));
					book.setReturned(rs.getLong("isReturned"));
					book.setDateOfRent(rs.getDate("date_of_rent"));
					book.setDeadline(rs.getDate("deadline"));
					book.setDateOfReturn(rs.getDate("date_of_return"));
					
					result.add(book);
				}
				return result;
			}

		});

		return result;
	}

	@Override
	public List<RentedBook> getAllRentedBooksForAdmin() {
		String sql = "SELECT Book.name AS name, Category.name AS category, CONCAT(Reader.name, ' ', Reader.surname) AS fullName, Rented_book.is_returned AS isReturned, "
				+ "    Rented_book.id AS id, Rented_book.date_of_rent as date_of_rent, Rented_book.deadline as deadline,Rented_book.date_of_return as date_of_return "
				+ "FROM Rented_book INNER JOIN Book ON Rented_book.id_book = Book.id INNER JOIN Reader ON Rented_book.id_reader = Reader.id INNER JOIN Category ON Book.category_id = Category.id "
				+ "WHERE Rented_book.is_returned= '0' ; ";
		List<RentedBook> result = jdbcTemplate.query(sql, new ResultSetExtractor<List<RentedBook>>() {

			@SuppressWarnings("null")
			@Override
			public List<RentedBook> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<RentedBook> result = new ArrayList<RentedBook>();

				RentedBook book = null;
				while (rs.next()) {
					book = new RentedBook();
					book.setName(rs.getString("name"));
					book.setCategory(rs.getString("category"));
					book.setReaderFullName(rs.getString("fullName"));
					book.setId(rs.getLong("id"));
					book.setReturned(rs.getLong("isReturned"));
					book.setDateOfRent(rs.getDate("date_of_rent"));
					book.setDeadline(rs.getDate("deadline"));
					book.setDateOfReturn(rs.getDate("date_of_return"));
					result.add(book);
				}
				return result;
			}

		});
		return result;
	}

	@Override
	public RentedBook returnBook(RentedBook rentedBook) {
		String sql = "UPDATE Rented_book " + "SET  is_returned = 1,date_of_return = current_date() " + "WHERE Rented_book.id  =  " + rentedBook.getId();
		rentedBook.setReturned(1);
		jdbcTemplate.update(sql);
		return rentedBook;
	}

	@Override
	public void addRentedBook(Long userId, Long bookId) {
		Date date = java.sql.Date.valueOf(LocalDate.now());
		Date returnDate = java.sql.Date.valueOf(LocalDate.now().plusMonths(2));
		String sql = "INSERT INTO `elibrary`.`rented_book` (`id_reader`, `id_book`, `date_of_rent`, `deadline`) VALUES ('"+ userId + "', '"+bookId+"', "+ date + ", "+returnDate+");";
		jdbcTemplate.update(sql);
	}

	@Override
	public List<RentedBook> getAlReturnedBooksForAdmin() {
		String sql = "SELECT Book.name AS name, Category.name AS category, CONCAT(Reader.name, ' ', Reader.surname) AS fullName, Rented_book.is_returned AS isReturned, "
				+ "    Rented_book.id AS id, Rented_book.date_of_rent as date_of_rent, Rented_book.deadline as deadline,Rented_book.date_of_return as date_of_return "
				+ "FROM Rented_book INNER JOIN Book ON Rented_book.id_book = Book.id INNER JOIN Reader ON Rented_book.id_reader = Reader.id INNER JOIN Category ON Book.category_id = Category.id "
				+ "WHERE Rented_book.is_returned= '1' ; ";
		List<RentedBook> result = jdbcTemplate.query(sql, new ResultSetExtractor<List<RentedBook>>() {

			@SuppressWarnings("null")
			@Override
			public List<RentedBook> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<RentedBook> result = new ArrayList<RentedBook>();

				RentedBook book = null;
				while (rs.next()) {
					book = new RentedBook();
					book.setName(rs.getString("name"));
					book.setCategory(rs.getString("category"));
					book.setReaderFullName(rs.getString("fullName"));
					book.setId(rs.getLong("id"));
					book.setReturned(rs.getLong("isReturned"));
					book.setDateOfRent(rs.getDate("date_of_rent"));
					book.setDeadline(rs.getDate("deadline"));
					book.setDateOfReturn(rs.getDate("date_of_return"));
					result.add(book);
				}
				return result;
			}

		});
		return result;
	}

}
