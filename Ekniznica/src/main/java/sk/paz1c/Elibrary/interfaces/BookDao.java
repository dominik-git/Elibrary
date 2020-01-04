package sk.paz1c.Elibrary.interfaces;

import java.util.List;

import sk.paz1c.Elibrary.model.Book;

public interface BookDao {
	
	public Book getBookById(long id);

	public Book getBookByName(String name);
	
	public Long deleteBookById(long id);
	
	public Book updateBook(Book book);
	
	public Book addBook(Book book);
	
	public List<Book> getAllBooks();
	
	public List<Book> getAllBooksByName(String name);
}
