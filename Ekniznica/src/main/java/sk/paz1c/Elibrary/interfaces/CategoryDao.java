package sk.paz1c.Elibrary.interfaces;

import java.util.List;

import sk.paz1c.Elibrary.model.Category;

public interface CategoryDao {
	public List<Category> getAllCategories();
	public Long deleteCategoryById(long id);
	public Category updateCategory(Category category);
	public Category addCategory(Category category);
	public Category getCategoryById(Long id);

}
