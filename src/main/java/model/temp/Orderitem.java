package model.temp;


public class Orderitem {
	private int recordId;
	private int bookId;
	private String title;
	private int amount;
	private int unitPrice;

	public Orderitem() {
	}

	public Orderitem(int bookId, String title, int amount,int unitPrice) {
		this.bookId = bookId;
		this.title = title;
		this.amount = amount;
		this.unitPrice = unitPrice;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
