package model.mysql;

import model.temp.Orderitem;


import javax.persistence.Transient;
import java.sql.Timestamp;
import java.util.List;

public class Order {

	private int orderId;
	private int userId;
	private Timestamp date;
    @Transient
    private List<Orderitem> orderitems;

	public Order() {
	}

	public Order(int userId, Timestamp date) {
		this.userId = userId;
		this.date = date;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public List<Orderitem> getOrderitems() {
		return orderitems;
	}

	public void setOrderitems(List<Orderitem> orderitems) {
		this.orderitems = orderitems;
	}
}
