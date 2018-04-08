package action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import model.mongo.UserDetail;
import model.mysql.User;
import service.UserService;

public class UsersAction extends BaseAction{

	private static final long serialVersionUID = 1L;

	private int userId;
	private String username;
	private String password;
	private String password2;
	private String role;
	private String phone;
	private String address;
	private String profile;
	private String imageUrl;
	private UserDetail userDetail;
	private String userDetailJSON;
	List<User> users;
	private InputStream inputStream;
	private Map<String,Object> jsonData;

	private UserService userService;

	public List<User> getUsers() {
		return userService.getAllUsers();
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword2() {
		return password2;
	}
	
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public Map<String, Object> getJsonData() {
		return jsonData;
	}

	public void setJsonData(Map<String, Object> jsonData) {
		this.jsonData = jsonData;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public InputStream getResult()
	{
		return inputStream;
	}

	public String addUser() throws Exception {

		User user = new User(username, password, role, phone, address);
		userService.addUser(user);

		return SUCCESS;
	}
	
	public String deleteUser() throws Exception {

		userService.deleteUser(userId);

		return SUCCESS;
	}
	
	public String updateUser() throws Exception {

		User user = userService.getUserById(userId);
		user.setUsername(username);
		user.setPassword(password);
		user.setRole(role);
		user.setPhone(phone);
		user.setAddress(address);
		userService.updateUser(user);

		return SUCCESS;
	}
	
	public String allUsers() throws Exception {

		users = userService.getAllUsers();

		return SUCCESS;
	}
	
	public String login() throws Exception {
		if (userService.login(username, password)) return SUCCESS;
		this.addActionError("Username or password wrong!");
		return ERROR;
		
	}
	
	public String signup() throws Exception {
		int signStat = userService.signup(username, password, password2, phone, address);
		if(signStat == 2) {
			this.addActionError("Two passwords differs!");
			return ERROR;
		}
		else if (signStat == 1) {
			this.addActionError("Username already exists!");
			return ERROR;
		}
		else {
			return SUCCESS;
		}
	}
	
	public String logout() throws Exception {
		userService.logout();
		return SUCCESS;
	}

	public String getUserDetail() throws Exception {
		userDetail = userService.getUserDetailById(userId);
		userDetailJSON = userService.detailToJsonStr(userDetail);
		inputStream = new ByteArrayInputStream(userDetailJSON.getBytes("UTF-8"));

		return SUCCESS;
	}

	public String changeProfile() throws Exception {
		userDetail = userService.getUserDetailById(userId);
		userDetail.setProfile(profile);
		userService.updateUserDetail(userDetail);

		return SUCCESS;
	}

	public String uploadAvator(){
		jsonData = new HashMap<String,Object>();

		try {
			userDetail = userService.getUserDetailById(userId);
			userService.updateAvator(userDetail, imageUrl);
			jsonData.put("result", "success");
			return SUCCESS;
		} catch (Exception e) {
			jsonData.put("result", "error");
			return ERROR;
		}
	}

	public String getUserInfo() {
		User user = userService.getUserById(userId);
		userDetail = userService.getUserDetailById(userId);
		username = user.getUsername();
		password = user.getPassword();
		phone = user.getPhone();
		address = user.getAddress();
		imageUrl = userDetail.getAvator();
		profile = userDetail.getProfile();

		return SUCCESS;
	}

	public String modifyUserInfo() {
		User user = userService.getUserById(userId);
		userDetail = userService.getUserDetailById(userId);
		user.setUsername(username);
		user.setPhone(phone);
		user.setAddress(address);
		userService.updateUser(user);
		userDetail.setProfile(profile);
		userService.updateUserDetail(userDetail);
		imageUrl = userDetail.getAvator();

		return SUCCESS;
	}

	public String updatePwd() {
		jsonData = new HashMap<String,Object>();
		if (!userService.getUserById(userId).getPassword().equals(password)){
			jsonData.put("result", "error");
		} else {
			User user = userService.getUserById(userId);
			user.setPassword(password2);
			userService.updateUser(user);
			jsonData.put("result", "success");
		}
		return SUCCESS;
	}
}
