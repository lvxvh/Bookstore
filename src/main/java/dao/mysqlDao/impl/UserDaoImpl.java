package dao.mysqlDao.impl;

import java.util.List;

import model.mysql.User;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import dao.mysqlDao.UserDao;


public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	public Integer save(User user) {
		getHibernateTemplate().save(user);
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) getHibernateTemplate().find(
				"from User as u where u.userId=(select max(userId) from User)");
		return users.size() > 0 ? users.get(0).getUserId() : -1;
	}

	public void delete(User user) {
		getHibernateTemplate().delete(user);
	}

	public void update(User user) {
		getHibernateTemplate().merge(user);
	}

	public User getUserById(int id) {
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) getHibernateTemplate().find(
				"from User as u where u.userId=?", id);
		return users.size() > 0 ? users.get(0) : null;
	}

	public User getUserByName(String name) {
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) getHibernateTemplate().find(
				"from User as u where u.username=?", name);
		User user = users.size() > 0 ? users.get(0) : null;
		return user;
	}
	
	public List<User> getAllUsers() {
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) getHibernateTemplate().find(
				"from User");
		return users;
	}

}
