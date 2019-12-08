package sk.paz1c.Elibrary.interfaces;

import java.util.List;

import sk.paz1c.Elibrary.model.Book;
import sk.paz1c.Elibrary.model.RentedBook;

public interface RentedBookDao {

	public List<RentedBook> getRentedBookById(long id);
	public List<RentedBook> getAllRenteBooksForAdmin();
	public RentedBook returnBook(RentedBook rentedBook);
	public List<RentedBook> getNonReturnedRentedBookById(long id);
}
