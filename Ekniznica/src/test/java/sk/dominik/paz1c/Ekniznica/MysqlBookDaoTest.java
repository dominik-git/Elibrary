package sk.dominik.paz1c.Ekniznica;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;
import java.util.List;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sk.paz1c.Elibrary.model.Book;
import sk.paz1c.Elibrary.mysql.DaoFactory;

class MysqlBookDaoTest {
	int pocet;
	Book book = new Book();
	List<Book> knihy;
	boolean vytvaraliSme = false;

	@BeforeEach
	void setUp() throws Exception {
		knihy = DaoFactory.INSTANCE.getBookDao().getAllBooks();
		pocet = knihy.size();
		book.setId(Long.getLong("20"));
		book.setAuthor("Jozko");
		book.setName("null");
		Date date = java.sql.Date.valueOf(LocalDate.now());
		book.setYearOfPublication(date);
		book.setCategory(DaoFactory.INSTANCE.getCategoryDao().getCategoryById(Integer.toUnsignedLong(4)));
		knihy.add(book);
	}

	@AfterEach
	void tearDown() throws Exception {
		if (vytvaraliSme) {
			DaoFactory.INSTANCE.getBookDao().deleteBookById(book.getId());
			vytvaraliSme = false;
		}
	}

	@Test
	void testAddBook() {

		DaoFactory.INSTANCE.getBookDao().addBook(book);
		vytvaraliSme = true;
		assertEquals(pocet + 1, DaoFactory.INSTANCE.getBookDao().getAllBooks().size());
	}

	@Test
	void testDeleteBookById() {
		DaoFactory.INSTANCE.getBookDao().addBook(book);
		vytvaraliSme = true;
		DaoFactory.INSTANCE.getBookDao().deleteBookById(book.getId());
		assertEquals(pocet, DaoFactory.INSTANCE.getBookDao().getAllBooks().size());
	}
	
	@Test
	void testGetBookById() {
		DaoFactory.INSTANCE.getBookDao().addBook(book);
		vytvaraliSme = true;
		DaoFactory.INSTANCE.getBookDao().getBookById(book.getId());
		assertEquals(book.getId(), DaoFactory.INSTANCE.getBookDao().getBookById(book.getId()).getId());
	}
	
	@Test
	void testGetBookByName() {
		DaoFactory.INSTANCE.getBookDao().addBook(book);
		vytvaraliSme = true;
		DaoFactory.INSTANCE.getBookDao().getBookByName(book.getName());
		assertEquals(book.getId(), DaoFactory.INSTANCE.getBookDao().getBookByName(book.getName()).getId());
		
	}

	@Test
	void testUpdateBook() {
		DaoFactory.INSTANCE.getBookDao().addBook(book);
		vytvaraliSme = true;
		String zmenaMena = "Vymenene";
		book.setName(zmenaMena);
		DaoFactory.INSTANCE.getBookDao().updateBook(book);
		assertEquals(zmenaMena, DaoFactory.INSTANCE.getBookDao().getBookById(book.getId()).getName());
	}

	@Test
	void testGetAllBooks() {
		assertEquals(pocet, DaoFactory.INSTANCE.getBookDao().getAllBooks().size());
		DaoFactory.INSTANCE.getBookDao().addBook(book);
		vytvaraliSme = true;
		assertEquals(pocet + 1, DaoFactory.INSTANCE.getBookDao().getAllBooks().size());
	}

	@Test
	void testGetAllBooksByName() {
		String name = "Lord";
		assertEquals(1, DaoFactory.INSTANCE.getBookDao().getAllBooksByName(name).size());
		name = "ahoj";
		assertEquals(0, DaoFactory.INSTANCE.getBookDao().getAllBooksByName(name).size());
		name = "";
		assertEquals(DaoFactory.INSTANCE.getBookDao().getAllBooks().size(), DaoFactory.INSTANCE.getBookDao().getAllBooksByName(name).size());

	}
	
	

}