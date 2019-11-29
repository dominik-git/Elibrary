package sk.paz1c.Elibrary.interfaces;

import java.util.List;

import sk.paz1c.Elibrary.model.Category;

public interface CategoryDao {
	public Category getCategoryById(long id);

	public Category getCategoryByName(String name);

	public List<Category> getAllCategories();

}
