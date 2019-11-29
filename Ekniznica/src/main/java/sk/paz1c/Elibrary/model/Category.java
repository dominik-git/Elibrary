package sk.paz1c.Elibrary.model;

public class Category {
	private long id;
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}

	private String name;

	public long getId() {
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
}
