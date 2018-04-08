package dao.mysqlDao.impl;

import java.util.List;

import model.mysql.Category;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import dao.mysqlDao.CategoryDao;

public class CategoryDaoImpl extends HibernateDaoSupport implements CategoryDao {

	public Integer save(Category category) {
		getHibernateTemplate().save(category);
		@SuppressWarnings("unchecked")
		List<Category> categories = (List<Category>) getHibernateTemplate().find(
				"from Category as c where c.categoryId=(select max(categoryId) from Category)");
		return categories.size() > 0 ? categories.get(0).getCategoryId() : -1;
	}

	public void delete(Category category) {
		getHibernateTemplate().delete(category);
	}

	public void update(Category category) {
		getHibernateTemplate().merge(category);
	}

	public Category getCategoryById(int id) {
		@SuppressWarnings("unchecked")
		List<Category> categories = (List<Category>) getHibernateTemplate().find(
				"from Category as c where c.categoryId=?", id);
		return categories.size() > 0 ? categories.get(0) : null;
	}

	public Category getCategoryByName(String name) {
		@SuppressWarnings("unchecked")
		List<Category> categories = (List<Category>) getHibernateTemplate().find(
				"from Category as c where c.name=?", name);
		Category category = categories.size() > 0 ? categories.get(0) : null;
		return category;
	}

	public List<Category> getAllCategories() {
		@SuppressWarnings("unchecked")
		List<Category> categories = (List<Category>) getHibernateTemplate()
				.find("from Category");
		return categories;
	}

}