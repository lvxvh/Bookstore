package model.mysql;

import dao.mongoDao.impl.BookDetailDaoImpl;
import model.mongo.BookDetail;
import service.BookService;
import service.impl.BookServiceImpl;

import java.util.Date;

public class Book {

	private int bookId;
	private String title;
	private String author;
	private int price;
	private String publisher;
	private Date date;
	private String isbn;
	private String category;

	public Book() {
	}

	public Book(String title, String author, int price, String publisher,
			Date date, String isbn, String category) {
		this.title = title;
		this.author = author;
		this.price = price;
		this.publisher = publisher;
		this.date = date;
		this.isbn = isbn;
		this.category = category;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
