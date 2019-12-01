package sk.paz1c.Elibrary.interfaces;

import java.util.List;

import sk.paz1c.Elibrary.model.Book;
import sk.paz1c.Elibrary.model.RentedBook;

public interface RentedBookDao {

	public List<Book> getRentedBookById(long id);
	public List<RentedBook> getAllRenteBooksForAdmin();
	public RentedBook returnBook(RentedBook rentedBook);
}
