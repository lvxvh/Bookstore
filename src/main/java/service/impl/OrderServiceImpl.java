package service.impl;

import java.sql.Timestamp;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.mongo.BookDetail;
import model.mongo.UserDetail;
import model.mysql.User;
import model.temp.Cart;
import model.temp.Orderitem;
import model.mongo.OrderDetail;
import model.mysql.Order;
import model.temp.Record;
import service.BookService;
import service.OrderService;
import dao.mongoDao.OrderDetailDao;
import dao.mysqlDao.OrderDao;

public class OrderServiceImpl implements OrderService{
	private OrderDao orderDao;
	private OrderDetailDao orderDetailDao;
	private BookService bookService;
	
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	/**
	 * order
	 * 
	 */
	public int addOrder(Order order){
		int orderId = orderDao.save(order);
		OrderDetail orderDetail = new OrderDetail(new ArrayList<>());
		orderDetail.setOrderId(orderId);
		orderDetailDao.save(orderDetail);
		return orderId;
	}

	public void deleteOrder(int orderId) {
		Order order = getOrderById(orderId);
		orderDao.delete(order);
		orderDetailDao.delete(orderDetailDao.getOrderDetailById(orderId));
	}

	public void updateOrder(Order order) {
		orderDao.update(order);
	}

	public Order getOrderById(int id) {
		return orderDao.getOrderById(id);
	}
	
	public Order getLatestOrder() {
		return orderDao.getLatestOrder();
	}
	
	public List<Order> getOrdersByUID(int uid) {
		return orderDao.getOrdersByUID(uid);
	}

	public List<Order> getAllOrders() {
		return orderDao.getAllOrders();
	}
	
	/**
	 * orderDetail
	 * 
	 */
	public OrderDetail getOrderDetailById(int orderId) {
		return orderDetailDao.getOrderDetailById(orderId);
	}
	
	public void deleteOrderDetail(int orderId) {
		orderDetailDao.delete(orderDetailDao.getOrderDetailById(orderId));
	}

	public String detailToJsonStr(OrderDetail orderDetail) {

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("orderitems", orderDetail.getOrderitems());
		return new Gson().toJson(result);
	}
	
	/**
	 * orderitem
	 * 
	 */
	public List<Orderitem> getOrderitemsById(int orderId) {
		List<Orderitem> resultOrderitems = new ArrayList<Orderitem>();
		
		OrderDetail orderDetail = getOrderDetailById(orderId);
		List<String> orderitems = orderDetail.getOrderitems();
		for(int i = 0 ; i < orderitems.size() ; i++) {
			  JsonObject obj=new JsonParser().parse(orderitems.get(i)).getAsJsonObject();
			  Orderitem item = new Orderitem();

			  JsonElement recordIdElement = obj.get("recordId");
			  item.setRecordId(recordIdElement.getAsInt());

			  JsonElement bookIdElement = obj.get("bookId");
			  item.setBookId(bookIdElement.getAsInt());

			  JsonElement titleIdElement = obj.get("title");
			  item.setTitle(titleIdElement.getAsString());

			  JsonElement amountElement = obj.get("amount");
			  item.setAmount(amountElement.getAsInt());

			  JsonElement unitPriceElement = obj.get("unitPrice");
			  item.setUnitPrice(unitPriceElement.getAsInt());

			  resultOrderitems.add(item);
		}
		return resultOrderitems;
	}
	
	public void addOrderitem(int orderId, Orderitem orderitem) {
		OrderDetail orderDetail = getOrderDetailById(orderId);
		List<Record> records = bookService.getRecordsById(orderitem.getBookId());
		int maxRecordId = 0;
		for(Record record : records) {
			maxRecordId = (record.getRecordId() > maxRecordId)? record.getRecordId() : maxRecordId;
		}
		Record record = new Record(maxRecordId + 1, orderDao.getOrderById(orderId).getUserId(),
									orderitem.getAmount(),new Timestamp(System.currentTimeMillis()));
		bookService.addRecord(orderitem.getBookId(), record);
		if (orderDetail == null) { // first item
			orderDetail = new OrderDetail();
			orderDetail.setOrderId(orderId);
			orderDetail.setOrderitems(new ArrayList<String>());
		}
		List<String> orderitems = orderDetail.getOrderitems();
		JsonObject obj = new JsonObject();

		obj.addProperty("recordId", maxRecordId + 1);
		obj.addProperty("bookId", orderitem.getBookId());
		obj.addProperty("title", orderitem.getTitle());
		obj.addProperty("amount", orderitem.getAmount());
		obj.addProperty("unitPrice", orderitem.getUnitPrice());
		
		orderitems.add(obj.toString());
		
		orderDetail.setOrderitems(orderitems);
		orderDetailDao.update(orderDetail);
	}

	public void deleteOrderitem(int orderId, Orderitem orderitem) {
		OrderDetail orderDetail = getOrderDetailById(orderId);
		List<String> orderitems = orderDetail.getOrderitems();
		int bookId = orderitem.getBookId();

		Record record = bookService.getRecordById(bookId, orderitem.getRecordId());
		bookService.deleteRecord(bookId, record);
		Iterator<String> it = orderitems.iterator();
		while(it.hasNext()) {
			String jsonStr = (String)it.next();
			JsonObject obj=new JsonParser().parse(jsonStr).getAsJsonObject();
			JsonElement bookIdElement = obj.get("bookId");
			
			if(bookIdElement.getAsInt() == bookId) {
				it.remove();
				break;
			}
		}
		orderDetail.setOrderitems(orderitems);
		orderDetailDao.update(orderDetail);
	}

	public void updateOrderitem(int orderId, Orderitem orderitem) {
		deleteOrderitem(orderId, orderitem);
		addOrderitem(orderId, orderitem);
	}

	public Orderitem getOrderitemByBookId(int orderId, int bookId) {	
		OrderDetail orderDetail = getOrderDetailById(orderId);
		List<String> orderitems = orderDetail.getOrderitems();
		Orderitem item = new Orderitem();
		
		Iterator<String> it = orderitems.iterator();	
		while(it.hasNext()) {
			String jsonStr = (String)it.next();
			JsonObject obj=new JsonParser().parse(jsonStr).getAsJsonObject();
			JsonElement bookIdElement = obj.get("bookId");
			
			if(bookIdElement.getAsInt() == bookId) {
				item.setBookId(bookId);
				JsonElement AmountElement = obj.get("amount");
				item.setAmount(AmountElement.getAsInt());
				break;
			}
		}
		return item;
	}

	public void checkout(User user, Cart cart) {
		Order order = new Order();

		order.setUserId(user.getUserId());
		order.setDate(new Timestamp(System.currentTimeMillis()));

		int orderId = addOrder(order);
		ArrayList<Orderitem> items = cart.getItems();
		for(Orderitem item : items) {
			addOrderitem(orderId, item);
		}
	}
}
