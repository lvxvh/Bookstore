package model.mongo;

import java.util.List;


public class BookDetail {

	private int bookId;
	private List<String> records;
	private String description;
	private String image;


	public BookDetail() {
	}

	public BookDetail(List<String> records, String description, String image) {
		this.records = records;  
		this.description = description;
		this.image = image;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public List<String> getRecords() {
		return records;
	}
	
	public void setRecords(List<String> records) {
		this.records = records;
	}
	 
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImage(){
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
}