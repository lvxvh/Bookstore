package dao.mongoDao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import dao.mongoDao.BookDetailDao;
import model.mongo.BookDetail;

public class BookDetailDaoImpl implements BookDetailDao{

	private MongoTemplate mongoTemplate;
	
	public void save(BookDetail bookDetail) {
		mongoTemplate.insert(bookDetail, "bookDetails");
	}

	public void delete(BookDetail bookDetail) {
		Query query = new Query();  
        query.addCriteria(new Criteria("bookId").is(bookDetail.getBookId()));
        mongoTemplate.findAndRemove(query, BookDetail.class,"bookDetails");
	}

	public void update(BookDetail bookDetail) {
		Query query = new Query();  
        query.addCriteria(new Criteria("bookId").is(bookDetail.getBookId()));  
        Update update = new Update();  
        update.set("records", bookDetail.getRecords());  
        update.set("description", bookDetail.getDescription());  
        update.set("image", bookDetail.getImage());  
         
        mongoTemplate.updateFirst(query, update, BookDetail.class,"bookDetails");
	}

	public BookDetail getBookDetailById(int bookId) {
		Query query = new Query();  
        query.addCriteria(new Criteria("bookId").is(bookId));  
        return mongoTemplate.findOne(query, BookDetail.class,"bookDetails"); 
	}

	public List<BookDetail> getAllBookDetails() {
		return mongoTemplate.findAll(BookDetail.class, "bookDetails");
	}

	// getter and setter
    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}
