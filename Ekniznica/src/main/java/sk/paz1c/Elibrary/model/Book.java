package sk.paz1c.Elibrary.model;

import java.sql.Date;
import java.time.LocalDateTime;

public class Book {
	
	public Book(Long id, String name, String author, String description, Category category, Date yearOfPublication) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.description = description;
		this.category = category;
		this.yearOfPublication = yearOfPublication;
	}
	public Book() {
		// TODO Auto-generated constructor stub
	}
	private Long id;
	private String name;
	private String author;
	private String description;
	private Category category;
	private Date yearOfPublication;
		
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Date getYearOfPublication() {
		return yearOfPublication;
	}
	public void setYearOfPublication(Date yearOfPublication) {
		this.yearOfPublication = yearOfPublication;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", description=" + description
				+ ", category=" + category + ", yearOfPublication=" + yearOfPublication + "]";
	}
	
	

}
