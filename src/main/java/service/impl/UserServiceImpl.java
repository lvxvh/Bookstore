package service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;

import dao.mongoDao.UserDetailDao;
import dao.mysqlDao.UserDao;
import model.mongo.UserDetail;
import model.mysql.User;
import service.UserService;
import util.ImageUtil;

public class UserServiceImpl implements UserService {
	private UserDao userDao;
	private UserDetailDao userDetailDao;
	private ImageUtil imageUtil;

	static final String defaultAvator = "http://bpic.588ku.com/element_origin_min_pic/01/55/09/6157474db9d7323.jpg";

	public void setUserDetailDao(UserDetailDao userDetailDao) {
		this.userDetailDao = userDetailDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setImageUtil(ImageUtil imageUtil) {
		this.imageUtil = imageUtil;
	}
	/**
	 * user
	 * 
	 */
	public void addUser(User user) throws Exception{
		int userId = userDao.save(user);
		UserDetail userDetail = new UserDetail("","");
		userDetail.setUserId(userId);
		updateAvator(userDetail,defaultAvator);
		userDetailDao.save(userDetail);
	}

	public void deleteUser(int userId) {
		User user = getUserById(userId);
		userDao.delete(user);
		userDetailDao.delete(userDetailDao.getUserDetailById(userId));
	}

	public void updateUser(User user) {
		userDao.update(user);
	}

	public User getUserById(int id) {
		return userDao.getUserById(id);
	}
	
	public User getUserByName(String name) {
		return userDao.getUserByName(name);
	}

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}
	
	public boolean login(String username, String password) {
		User user = getUserByName(username);
		if (user != null && user.getPassword().equals(password)){
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("user", user);
			return true;
		}
		return false;
	}
	
	public int signup(String username, String password, String password2, String phone, String address) throws Exception{
		if (!password.equals(password2)) {
			return 2;
		}
		else if (getUserByName(username) != null) {
			return 1;
		}
		else {
			User user = new User(username, password, "user", phone, address);
			addUser(user);
			return 0;
		}
	}
	
	public void logout() {
		ActionContext.getContext().getSession().clear();
	}

	public String detailToJsonStr(UserDetail userDetail) {

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("avator", userDetail.getAvator());
		result.put("profile", userDetail.getProfile());
		return new Gson().toJson(result);
	}

	/**
	 * userDetail
	 */
	public void addUserDetail(UserDetail userDetail) {
		userDetailDao.save(userDetail);
	}

	public void deleteUserDetail(int userId) {
		userDetailDao.delete(userDetailDao.getUserDetailById(userId));
	}
	
	public void updateUserDetail(UserDetail userDetail) {
		userDetailDao.update(userDetail);
	}
	
	public UserDetail getUserDetailById(int userId) {
		return userDetailDao.getUserDetailById(userId);
	}

	public void updateAvator(UserDetail userDetail, String imageUrl) throws Exception{
		String imgCode = imageUtil.getURLImage(imageUrl);
		userDetail.setAvator(imgCode);
		updateUserDetail(userDetail);
	}

}
