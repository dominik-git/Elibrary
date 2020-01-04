package sk.paz1c.Elibrary.model;

import java.sql.Date;

public class RentedBook {

	private Long id;
	private Boolean isReturned;
	private Date dateOfRent;
	private Date deadline;
	private Date dateOfReturn;

	private Book book;
	private Reader reader;

	public RentedBook() {

	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Boolean getIsReturned() {
		return isReturned;
	}

	public void setIsReturned(int value) {

		isReturned = (value == 1) ? true : false;
		this.isReturned = isReturned;
	}

	public Date getDateOfRent() {
		return dateOfRent;
	}

	public void setDateOfRent(Date dateOfRent) {
		this.dateOfRent = dateOfRent;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Date getDateOfReturn() {
		return dateOfReturn;
	}

	public void setDateOfReturn(Date dateOfReturn) {
		this.dateOfReturn = dateOfReturn;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	@Override
	public String toString() {
		return "RentedBook [id=" + id + ", isReturned=" + isReturned + ", dateOfRent=" + dateOfRent + ", deadline="
				+ deadline + ", dateOfReturn=" + dateOfReturn + ", book=" + book + ", reader=" + reader + "]";
	}

}
