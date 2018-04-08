package model.mongo;


public class UserDetail {
	private int userId;
	private String profile;
	private String avator;
	

	public UserDetail() {
	}

	public UserDetail(String profile, String avator) {
		this.profile = profile;  
		this.avator = avator;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getProfile() {
		return profile;
	}
	
	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	public String getAvator(){
		return avator;
	}
	
	public void setAvator(String avator) {
		this.avator = avator;
	}
	
}
