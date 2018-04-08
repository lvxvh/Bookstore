package model.mongo;

import java.util.List;

public class OrderDetail {

	private int orderId;
	private List<String> orderitems;
	

	public OrderDetail() {
	}

	public OrderDetail(List<String> orderitems) {
		this.orderitems = orderitems;  
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public List<String> getOrderitems() {
		return orderitems;
	}
	
	public void setOrderitems(List<String> orderitems) {
		this.orderitems = orderitems;
	}
	
}