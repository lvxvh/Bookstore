package dao.mysqlDao;

import java.util.List;

import model.mysql.Category;

public interface CategoryDao {

	public Integer save(Category category);

	public void delete(Category category);

	public void update(Category category);

	public Category getCategoryById(int id);

	public Category getCategoryByName(String name);

	public List<Category> getAllCategories();

}