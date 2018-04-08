package model.temp;

import java.sql.Timestamp;

public class Record {
	private int recordId;
	private int userId;
	private int amount;
	private Timestamp date;
	

	public Record() {
	}

	public Record(int recordId, int userId, int amount, Timestamp date) {
		this.recordId = recordId;
		this.userId = userId;
		this.amount = amount;
		this.date = date;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public Timestamp getDate() {
		return date;
	}
	
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
}
