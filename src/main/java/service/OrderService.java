package service;

import java.util.List;


import model.mysql.Order;
import model.mysql.User;
import model.temp.Cart;
import model.temp.Orderitem;
import model.mongo.OrderDetail;


public interface OrderService {
	/**
	 * order
	 * 
	 */
	public int addOrder(Order order);

	public void deleteOrder(int orderId);

	public void updateOrder(Order order);

	public Order getOrderById(int id);
	
	public Order getLatestOrder();
	
	public List<Order> getOrdersByUID(int uid);

	public List<Order> getAllOrders();

	/**
	 * orderDetail
	 * 
	 */
	public OrderDetail getOrderDetailById(int orderId);
	
	public void deleteOrderDetail(int orderId);

	public String detailToJsonStr(OrderDetail orderDetail);
	/**
	 * orderitem
	 * 
	 */
	public List<Orderitem> getOrderitemsById(int orderId);
	
	public void addOrderitem(int orderId, Orderitem orderitem);

	public void deleteOrderitem(int orderId, Orderitem orderitem);

	public void updateOrderitem(int orderId, Orderitem orderitem);

	public Orderitem getOrderitemByBookId(int orderId, int bookId);

	/**
	 * other
	 */
	public void checkout(User user, Cart cart);
	
}