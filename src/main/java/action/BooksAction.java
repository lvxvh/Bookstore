package action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

import model.mysql.Category;
import model.temp.BookInfo;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionContext;

import model.mongo.BookDetail;
import model.mysql.Book;
import model.temp.Record;
import service.BookService;

import javax.persistence.Convert;

public class BooksAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	
	private int bookId;
	private String title;
	private String author;
	private int price;
	private String publisher;
	private Date date;
	private String image;
	private String isbn;
	private List<Record> records;
	private String description;
	private String imageUrl;
	private String keyword;
	private BookDetail bookDetail;
	private Book book;
	private String bookDetailJSON;
	private int categoryId;
	private String category;
	private String name;
	private InputStream inputStream;
	private Map<String,Object> jsonData;
	private List<BookInfo> bookInfos;


	private BookService bookService;
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getImage() {
		return bookService.getBookDetailById(bookId).getImage();
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Record> getRecords() {
		return bookService.getRecordsById(bookId);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public InputStream getResult()
	{
		return inputStream;
	}

	public String getBookDetailJSON() {
		return bookDetailJSON;
	}

	public void setBookDetailJSON(String bookDetailJSON) {
		this.bookDetailJSON = bookDetailJSON;
	}

	public void setBookDetail(BookDetail bookDetail) {
		this.bookDetail = bookDetail;
	}

	public BookDetail getBookDetail(){return bookDetail;}

	public void setBook(Book book) {
		this.book = book;
	}

	public Book getBook() {
		return book;
	}

	public Map<String, Object> getJsonData() {
		return jsonData;
	}

	public void setJsonData(Map<String, Object> jsonData) {
		this.jsonData = jsonData;
	}

	public List<BookInfo> getBookInfos() {
		return bookInfos;
	}

	public void setBookInfos(List<BookInfo> bookInfos) {
		this.bookInfos = bookInfos;
	}

	public String addBook() throws Exception {
		Date date = new Date();
		Book book = new Book(title, author, price, publisher, date, isbn, category);
		  //bookDetail 单独设置
		bookService.addBook(book);

		return SUCCESS;
	}
	
	public String deleteBook() throws Exception {
		bookService.deleteBook(bookId);

		return SUCCESS;
	}
	
	public String updateBook() throws Exception {

		Book book = bookService.getBookById(bookId);
		book.setTitle(title);
		book.setAuthor(author);
		book.setPrice(price);
		book.setPublisher(publisher);
		book.setIsbn(isbn);
		book.setCategory(category);
		bookService.updateBook(book);

		return SUCCESS;
	}

	public String getBookDetailAJAX() throws Exception {
		bookDetail = bookService.getBookDetailById(bookId);
		bookDetailJSON = bookService.detailToJsonStr(bookDetail);
		inputStream = new ByteArrayInputStream(bookDetailJSON.getBytes("UTF-8"));

		return SUCCESS;
	}

	public String getBookRecord() throws Exception {
		bookDetail = bookService.getBookDetailById(bookId);
		bookDetailJSON = bookService.detailToJsonStr(bookDetail);
		inputStream = new ByteArrayInputStream(bookDetailJSON.getBytes("UTF-8"));

		return SUCCESS;
	}

	public String changeDescription() throws Exception {
		bookDetail = bookService.getBookDetailById(bookId);
		bookDetail.setDescription(description);
		bookService.updateBookDetail(bookDetail);

		return SUCCESS;
	}

	public String uploadImage() throws Exception {
		jsonData = new HashMap<String,Object>();
		try {
			bookDetail = bookService.getBookDetailById(bookId);
			bookService.updateImage(bookDetail, imageUrl);
			jsonData.put("result", "success");
			return SUCCESS;
		} catch (Exception e) {
			jsonData.put("result", "error");
			return ERROR;
		}
	}

	public String getMoreInfo() {
		book = bookService.getBookById(bookId);
		bookDetail = bookService.getBookDetailById(bookId);
		return SUCCESS;
	}

	public List<Book> getBooks()   //books
	{
		return bookService.getAllBooks();
	}

	public String bookInfoByKey() {
		bookInfos = bookService.searchBookFor(keyword);
		return SUCCESS;
	}

	public String allBookInfos() {
		  bookInfos = bookService.getAllBookInfos();
		  return SUCCESS;
	}

	public List<Category> getCategories() {
		return bookService.getAllCategories();
	}

	public String addCategory() {
		jsonData = new HashMap<String,Object>();
		Category category = new Category(name);
		if(bookService.addCategory(category) == 1) {
			jsonData.put("result", "error");
		} else {
			jsonData.put("result", "success");
		}
		return SUCCESS;
	}

	public String deleteCategory() {
		Category category = bookService.getCategoryById(categoryId);
		bookService.deleteCategory(category);
		return SUCCESS;
	}

	public String getBooksByCategory() {
		bookInfos = bookService.getBookInfoByCategory(name);
		return SUCCESS;
	}
}
