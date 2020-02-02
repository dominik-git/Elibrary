package sk.paz1c.Elibrary.model;

public class Category {
	
	public Category() {
	}
	
	public Category(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	private Long id;
	private String name;

	
	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
}
