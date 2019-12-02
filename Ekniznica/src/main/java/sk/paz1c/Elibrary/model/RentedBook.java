package sk.paz1c.Elibrary.model;

import java.sql.Date;

public class RentedBook {

	private String name;
	private String category;
	private String readerFullName;
	private long id;
	private boolean isReturned;
	private Date dateOfRent;
	private Date deadline;
	private Date dateOfReturn;

	public RentedBook() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getReaderFullName() {
		return readerFullName;
	}

	public void setReaderFullName(String readerFullName) {
		this.readerFullName = readerFullName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean getIsReturned() {
		return isReturned;
	}

	public void setReturned(long l) {
		
		this.isReturned = (l == 1 )? true :false;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((dateOfRent == null) ? 0 : dateOfRent.hashCode());
		result = prime * result + ((dateOfReturn == null) ? 0 : dateOfReturn.hashCode());
		result = prime * result + ((deadline == null) ? 0 : deadline.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (isReturned ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((readerFullName == null) ? 0 : readerFullName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RentedBook other = (RentedBook) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (dateOfRent == null) {
			if (other.dateOfRent != null)
				return false;
		} else if (!dateOfRent.equals(other.dateOfRent))
			return false;
		if (dateOfReturn == null) {
			if (other.dateOfReturn != null)
				return false;
		} else if (!dateOfReturn.equals(other.dateOfReturn))
			return false;
		if (deadline == null) {
			if (other.deadline != null)
				return false;
		} else if (!deadline.equals(other.deadline))
			return false;
		if (id != other.id)
			return false;
		if (isReturned != other.isReturned)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (readerFullName == null) {
			if (other.readerFullName != null)
				return false;
		} else if (!readerFullName.equals(other.readerFullName))
			return false;
		return true;
	}
	



}
