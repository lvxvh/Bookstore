package service;

import java.util.List;
import model.mysql.Book;
import model.mongo.BookDetail;
import model.mysql.Category;
import model.temp.BookInfo;
import model.temp.Record;



public interface BookService {

	/**
	 * book
	 * 
	 */
	public void addBook(Book book);

	public void deleteBook(int bookId);

	public void updateBook(Book book);

	public Book getBookById(int id);

	public List<BookInfo> searchBookFor(String keyword);
	
	public List<Book> getAllBooks();
	
	/**
	 * bookDetail
	 */
	
	public void updateBookDetail(BookDetail bookDetail);
	
	public BookDetail getBookDetailById(int bookId);

	public String detailToJsonStr(BookDetail bookDetail);

	public void updateImage(BookDetail bookDetail, String imageUrl) throws Exception;
	
	/**
	 * saleRecord  针对record的crud
	 */
	public Record getRecordById(int bookId, int recordId);

	public List<Record> getRecordsById(int bookId);
	
	public void addRecord(int bookId, Record record);

	public void deleteRecord(int bookId, Record record);

	public void updateRecord(int bookId, Record record);

	public List<BookInfo> getAllBookInfos();

	/**
	 * category
	 */
	public List<Category> getAllCategories();

	public int addCategory(Category category);

	public Category getCategoryById(int categoryId);

	public void deleteCategory(Category category);

	public List<BookInfo> getBookInfoByCategory(String name);
}
