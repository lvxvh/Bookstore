package service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dao.mysqlDao.CategoryDao;
import model.mysql.Category;
import model.temp.BookInfo;
import service.BookService;
import dao.mongoDao.BookDetailDao;
import dao.mysqlDao.BookDao;
import model.mongo.BookDetail;
import model.mysql.Book;
import model.temp.Record;
import util.ImageUtil;

public class BookServiceImpl implements BookService{

	private BookDao bookDao;
	private BookDetailDao bookDetailDao;
	private CategoryDao categoryDao;
	private ImageUtil imageUtil;
	
	public void setBookDetailDao(BookDetailDao bookDetailDao) {
		this.bookDetailDao = bookDetailDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public void setImageUtil(ImageUtil imageUtil) {
		this.imageUtil = imageUtil;
	}

	/**
	 * book
	 * 
	 */
	public void addBook(Book book) {
		int bookId = bookDao.save(book);
		BookDetail bookDetail = new BookDetail(new ArrayList<>(), new String(), new String());
		bookDetail.setBookId(bookId);
		bookDetailDao.save(bookDetail);
	}

	public void deleteBook(int bookId) {
			Book book = getBookById(bookId);
			bookDao.delete(book);
			bookDetailDao.delete(bookDetailDao.getBookDetailById(bookId));
	}

	public void updateBook(Book book) {
		bookDao.update(book);
	}

	public Book getBookById(int id) {
		return bookDao.getBookById(id);
	}

	public List<BookInfo> searchBookFor(String keyword) {
		List<BookInfo> resultBooks = new ArrayList<>();
		List<BookInfo> allBooks = this.getAllBookInfos();
		for (BookInfo book : allBooks){
			if (book.getTitle().contains(keyword)) {
				resultBooks.add(book);
			}
		}
		return resultBooks;
	}
	
	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
	}
	
	/**
	 * bookDetail
	 */
	
	public void updateBookDetail(BookDetail bookDetail) {
		bookDetailDao.update(bookDetail);
	}
	
	public BookDetail getBookDetailById(int bookId) {
		return bookDetailDao.getBookDetailById(bookId);
	}

	public String detailToJsonStr(BookDetail bookDetail) {

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("description", bookDetail.getDescription());
		result.put("image", bookDetail.getImage());
		result.put("records", bookDetail.getRecords());

		return new Gson().toJson(result);
	}

	public void updateImage(BookDetail bookDetail, String imageUrl) throws Exception{
		String imgCode = imageUtil.getURLImage(imageUrl);
		bookDetail.setImage(imgCode);
		updateBookDetail(bookDetail);
	}

	/**
	 * saleRecord  针对record的crud
	 */
	public Record getRecordById(int bookId, int recordId) {
		List<Record> records = getRecordsById(bookId);
		for (Record record : records) {
			if(record.getRecordId() == recordId) {
				return record;
			}
		}
		return null;
	}

	public List<Record> getRecordsById(int bookId) {
		List<Record> resultRecords = new ArrayList<Record>();
		BookDetail bookDetail = getBookDetailById(bookId);
		List<String> records = bookDetail.getRecords();
		for(int i = 0 ; i < records.size() ; i++) {
			  JsonObject obj=new JsonParser().parse(records.get(i)).getAsJsonObject();
			  Record record = new Record();

			  JsonElement recordIdElement = obj.get("recordId");
			  record.setRecordId(recordIdElement.getAsInt());

			  JsonElement userIdElement = obj.get("userId");
			  record.setUserId(userIdElement.getAsInt());
			  
			  JsonElement amountElement = obj.get("amount");
			  record.setAmount(amountElement.getAsInt());
			  
			  JsonElement dateElement = obj.get("date");	//timestamp 转换 string
			  Timestamp date = Timestamp.valueOf(dateElement.getAsString());
			  record.setDate(date);
			  
			  resultRecords.add(record);
		}
		return resultRecords;
	}
	
	public void addRecord(int bookId, Record record) {
		BookDetail bookDetail = getBookDetailById(bookId);
		//bookDetail 不会为空
		List<String> records = bookDetail.getRecords();
		JsonObject obj = new JsonObject();

		obj.addProperty("recordId", record.getRecordId());
		obj.addProperty("userId", record.getUserId());
		obj.addProperty("amount", record.getAmount());
		obj.addProperty("date", record.getDate().toString());
		
		records.add(obj.toString());
		
		bookDetail.setRecords(records);
		bookDetailDao.update(bookDetail);
	}

	public void deleteRecord(int bookId, Record record) {
		BookDetail bookDetail = getBookDetailById(bookId);
		List<String> records = bookDetail.getRecords();
		int recordId = record.getRecordId();
		
		Iterator<String> it = records.iterator();
		while(it.hasNext()) {
			String jsonStr = (String)it.next();
			JsonObject obj=new JsonParser().parse(jsonStr).getAsJsonObject();
			JsonElement recordIdElement = obj.get("recordId");

			
			if(recordIdElement.getAsInt() == recordId) {
				it.remove();
				break;
			}
		}
		bookDetail.setRecords(records);
		bookDetailDao.update(bookDetail);
	}

	public void updateRecord(int bookId, Record record) {
		deleteRecord(bookId, record);
		addRecord(bookId, record);
	}

	public List<BookInfo> getAllBookInfos() {
		int bookId; String image;
		List<BookInfo> bookInfos = new ArrayList<>();
		List<Book> books = getAllBooks();
		for (Book book : books) {
			bookId = book.getBookId();
			image = getBookDetailById(bookId).getImage();
			BookInfo bookInfo = new BookInfo(bookId,image,book.getTitle(),book.getPrice(),book.getCategory());
			bookInfos.add(bookInfo);
		}
		return bookInfos;
	}


	public List<Category> getAllCategories() {
		return categoryDao.getAllCategories();
	}

	public int addCategory(Category category) {
		if(categoryDao.getCategoryByName(category.getName()) != null) {
			return 1;
		}
		categoryDao.save(category);
		return 0;
	}

	public Category getCategoryById(int categoryId) {
		return categoryDao.getCategoryById(categoryId);
	}

	public void deleteCategory(Category category) {
		categoryDao.delete(category);
		List<Book> books = getAllBooks();
		for(Book book : books){
			if(book.getCategory().equals(category.getName())){
				book.setCategory("");
				updateBook(book);
			}
		}
	}

	public List<BookInfo> getBookInfoByCategory(String name) {
		List<BookInfo> resultBooks = new ArrayList<>();
		List<BookInfo> allBooks = this.getAllBookInfos();
		for (BookInfo book : allBooks){
			if (book.getCategory().equals(name)) {
				resultBooks.add(book);
			}
		}
		return resultBooks;
	}
}
