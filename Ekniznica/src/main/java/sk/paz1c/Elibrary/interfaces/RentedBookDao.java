package sk.paz1c.Elibrary.interfaces;

import java.util.List;

import sk.paz1c.Elibrary.model.Book;
import sk.paz1c.Elibrary.model.RentedBook;

public interface RentedBookDao {

	public List<RentedBook> getRentedBookById(long id);
	public List<RentedBook> getAllRentedBooksForAdmin();
	public List<RentedBook> getAlReturnedBooksForAdmin();
	public RentedBook returnBook(RentedBook rentedBook);
	public List<RentedBook> getNonReturnedRentedBookByReaderId(long id);
	public void addRentedBook(Long userId, Long bookId);
	
}
