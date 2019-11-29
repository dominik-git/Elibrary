package sk.paz1c.Elibrary.mysql;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;

import sk.paz1c.Elibrary.interfaces.BookDao;
import sk.paz1c.Elibrary.interfaces.CategoryDao;
import sk.paz1c.Elibrary.interfaces.ReaderDao;
import sk.paz1c.Elibrary.interfaces.RentedBookDao;

public enum DaoFactory {
	INSTANCE;

	public JdbcTemplate jdbcTemplate;
	private ReaderDao readerDao;
	private BookDao bookDao;
	private RentedBookDao rentedBookDao;
	private CategoryDao categoryDao;

	public ReaderDao getReaderDao() {
		if (readerDao == null) {
			readerDao = new MysqlReaderDao(getJdbcTemplate());
		}
		return readerDao;
	}
	public CategoryDao getCategoryDao() {
		if(categoryDao == null) {
			categoryDao = new MysqlCategoryDao(getJdbcTemplate());
		}
		return categoryDao;
	}
	public BookDao getBookDao() {
		if (bookDao == null) {
			bookDao = new MysqlBookDao(getJdbcTemplate());
		}
		return bookDao;
	}

	public RentedBookDao getRentedBookDao() {
		if (rentedBookDao == null) {
			rentedBookDao = new MysqlRentedBookDao(getJdbcTemplate());
		}
		return rentedBookDao;
	}

	public JdbcTemplate getJdbcTemplate() {
		if (jdbcTemplate == null) {
			MysqlDataSource dataSource = new MysqlDataSource();
			// dataSource.setDatabaseName("elibrary");
			dataSource.setUser("root");
			dataSource.setPassword("Heslo.123");

			dataSource.setUrl("jdbc:mysql://localhost/eLibrary?serverTimezone=Europe/Bratislava");
			jdbcTemplate = new JdbcTemplate(dataSource);
		}
		return jdbcTemplate;
	}
}
