package service;

import java.io.IOException;
import java.util.List;


import model.mongo.UserDetail;
import model.mysql.User;



public interface UserService {
	/**
	 * user
	 * 
	 */
	public void addUser(User user) throws Exception;

	public void deleteUser(int userId);

	public void updateUser(User user);

	public User getUserById(int id);
	
	public User getUserByName(String name);

	public List<User> getAllUsers();
	
	public boolean login(String username, String password);
	
	public int signup(String username, String password, String password2, String phone, String address)throws Exception;
	
	public void logout();

	public String detailToJsonStr(UserDetail userDetail);
	/**
	 * userDetail
	 */
	public void addUserDetail(UserDetail userDetail);

	public void deleteUserDetail(int userId);
	
	public void updateUserDetail(UserDetail userDetail);
	
	public UserDetail getUserDetailById(int userId);

	public void updateAvator(UserDetail userDetail, String imageUrl) throws Exception;
	
}
