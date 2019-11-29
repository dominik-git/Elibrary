package sk.paz1c.Elibrary.interfaces;

import java.util.List;

import sk.paz1c.Elibrary.model.Book;

public interface BookDao {
	public Book getBookById(long id);

	public Book getBookByName(String name);
	
	public Book getBookByYear(int year);
	
	public List<Book> getBooksByCategoryId(long categoryId);
	
	public void addBook(Book book);
	
	public void deleteBookById(long id);
	
	public List<Book> getAllBooks();
}
