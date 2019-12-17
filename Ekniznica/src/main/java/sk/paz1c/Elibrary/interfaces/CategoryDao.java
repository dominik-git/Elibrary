package sk.paz1c.Elibrary.interfaces;

import java.util.List;

import sk.paz1c.Elibrary.model.Category;

public interface CategoryDao {
	public List<Category> getAllCategories();
	
	public Category addCategory(Category category);

}
