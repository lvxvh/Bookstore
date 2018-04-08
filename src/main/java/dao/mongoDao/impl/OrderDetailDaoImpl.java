package dao.mongoDao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import dao.mongoDao.OrderDetailDao;
import model.mongo.OrderDetail;

public class OrderDetailDaoImpl implements OrderDetailDao{

	private MongoTemplate mongoTemplate;
	
	public void save(OrderDetail orderDetail) {
		mongoTemplate.insert(orderDetail, "orderDetails");
	}

	public void delete(OrderDetail orderDetail) {
		Query query = new Query();  
        query.addCriteria(new Criteria("orderId").is(orderDetail.getOrderId()));
        mongoTemplate.findAndRemove(query, OrderDetail.class,"orderDetails");
	}

	public void update(OrderDetail orderDetail) {
		Query query = new Query();  
        query.addCriteria(new Criteria("orderId").is(orderDetail.getOrderId()));  
        Update update = new Update();  
        update.set("orderitems", orderDetail.getOrderitems());  
         
        mongoTemplate.updateFirst(query, update, OrderDetail.class,"orderDetails");
	}

	public OrderDetail getOrderDetailById(int orderId) {
		Query query = new Query();  
        query.addCriteria(new Criteria("orderId").is(orderId));  
        return mongoTemplate.findOne(query, OrderDetail.class,"orderDetails"); 
	}

	public List<OrderDetail> getAllOrderDetails() {
		return mongoTemplate.findAll(OrderDetail.class, "orderDetails");
	}

	// getter and setter
    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}