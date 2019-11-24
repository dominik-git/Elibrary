package sk.paz1c.Elibrary.interfaces;

import java.util.List;

import sk.paz1c.Elibrary.model.Book;

public interface RentedBookDao {

	public List<Book> getRentedBookById(long id);
}
