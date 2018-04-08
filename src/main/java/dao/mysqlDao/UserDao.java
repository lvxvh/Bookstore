package dao.mysqlDao;

import java.util.List;

import model.mysql.User;

public interface UserDao {

	public Integer save(User user);

	public void delete(User user);

	public void update(User user);

	public User getUserById(int id);

	public User getUserByName(String name);
	
	public List<User> getAllUsers();

}