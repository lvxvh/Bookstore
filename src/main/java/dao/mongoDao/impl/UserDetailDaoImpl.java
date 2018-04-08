package dao.mongoDao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import dao.mongoDao.UserDetailDao;
import model.mongo.UserDetail;

public class UserDetailDaoImpl implements UserDetailDao{

	private MongoTemplate mongoTemplate;
	
	public void save(UserDetail userDetail) {
		mongoTemplate.insert(userDetail, "userDetails");
	}

	public void delete(UserDetail userDetail) {
		Query query = new Query();  
        query.addCriteria(new Criteria("userId").is(userDetail.getUserId()));
        mongoTemplate.findAndRemove(query, UserDetail.class,"userDetails");
	}

	public void update(UserDetail userDetail) {
		Query query = new Query();  
        query.addCriteria(new Criteria("userId").is(userDetail.getUserId()));  
        Update update = new Update();  
        update.set("profile", userDetail.getProfile());  
        update.set("avator", userDetail.getAvator());
         
        mongoTemplate.updateFirst(query, update, UserDetail.class,"userDetails");
	}

	public UserDetail getUserDetailById(int userId) {
		Query query = new Query();  
        query.addCriteria(new Criteria("userId").is(userId));  
        return mongoTemplate.findOne(query, UserDetail.class,"userDetails"); 
	}

	public List<UserDetail> getAllUserDetails() {
		return mongoTemplate.findAll(UserDetail.class, "userDetails");
	}

	// getter and setter
    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}