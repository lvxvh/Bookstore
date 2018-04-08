package dao.mongoDao;

import java.util.List;

import model.mongo.UserDetail;

public interface UserDetailDao {

	public void save(UserDetail userDetail);

	public void delete(UserDetail userDetail);

	public void update(UserDetail userDetail);

	public UserDetail getUserDetailById(int userId);

	public List<UserDetail> getAllUserDetails();
}
