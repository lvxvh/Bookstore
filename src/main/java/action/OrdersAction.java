package action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Timestamp;

import java.util.List;

import java.util.Map;
import java.util.Set;
import java.util.HashSet;


import com.opensymphony.xwork2.ActionContext;

import model.mongo.OrderDetail;
import model.mysql.Book;
import model.mysql.Order;
import model.mysql.User;
import model.temp.Orderitem;
import service.BookService;
import service.OrderService;
import service.UserService;

public class OrdersAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private int orderId;
	private int userId;
	private Timestamp date;
	private List<User> users;
	private List<Order> orders;
    private List<Order> ordersInfo;
	private List<Book> books;
	private OrderDetail orderDetail;
	private String orderDetailJSON;
	private InputStream inputStream;
	private int bookId;
	private int amount;
	private int recordId;

	private OrderService orderService;
	private UserService userService;
	private BookService bookService;


	public List<Order> getOrders() {
		return orderService.getAllOrders();
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

    public void setOrdersInfo(List<Order> ordersInfo) {
        this.ordersInfo = ordersInfo;
    }

    public List<Order> getOrdersInfo() {
        return ordersInfo;
    }

    public List<User> getUsers() {
		return userService.getAllUsers();
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Book> getBooks() {
		return bookService.getAllBooks();
	}

	public void setBooks(List<Book> books) {
		this.books = books;
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

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public InputStream getResult()
	{
		return inputStream;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public int getRecordId() {
		return recordId;
	}

	public String addOrder() throws Exception {

		Timestamp date = new Timestamp(System.currentTimeMillis());
		Order order = new Order(userId, date);
		orderService.addOrder(order);

		return SUCCESS;
	}
	
	public String deleteOrder() throws Exception {

		orderService.deleteOrder(orderId);

		return SUCCESS;
	}
	
	public String updateOrder() throws Exception {

		Timestamp date = new Timestamp(System.currentTimeMillis());
		Order order = orderService.getOrderById(orderId);
		order.setUserId(userId);
		order.setDate(date);
		orderService.updateOrder(order);


		return SUCCESS;
	}                                            //update order detail..

	public String getOrderDetail() throws Exception {
		orderDetail = orderService.getOrderDetailById(orderId);
		orderDetailJSON = orderService.detailToJsonStr(orderDetail);
		inputStream = new ByteArrayInputStream(orderDetailJSON.getBytes("UTF-8"));

		return SUCCESS;
	}
	//update 要用到recordId
	public String updateOrderitem () {
		int unitPrice = bookService.getBookById(bookId).getPrice();
		String title = bookService.getBookById(bookId).getTitle();
		Orderitem orderitem= new Orderitem(bookId, title, amount, unitPrice);
		orderitem.setUnitPrice(recordId);
		orderService.updateOrderitem(orderId, orderitem);

		return SUCCESS;
	}

	public String addOrderitem() {
		int unitPrice = bookService.getBookById(bookId).getPrice();
		String title = bookService.getBookById(bookId).getTitle();
		Orderitem orderitem = new Orderitem(bookId, title, amount, unitPrice);
		orderService.addOrderitem(orderId, orderitem);

		return SUCCESS;
	}
		//delete 也要
	public String deleteOrderitem() {
		int unitPrice = bookService.getBookById(bookId).getPrice();
		String title = bookService.getBookById(bookId).getTitle();
		Orderitem orderitem = new Orderitem(bookId, title, amount, unitPrice);
		orderitem.setRecordId(recordId);
		orderService.deleteOrderitem(orderId, orderitem);

		return SUCCESS;
	}
	/*public String allOrders() throws Exception {

		List<Order> orders = orderService.getAllOrders();
		request().setAttribute("orders", orders);

		List<User> users = orderService.getAllUsers();
		request().setAttribute("users", users);
		
		List<Book> books = orderService.getAllBooks();
		request().setAttribute("books", books);

		return SUCCESS;
	}*/
	
	public String getUserOrder() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User)session.get("user");
		List<Order> orders = orderService.getOrdersByUID(user.getUserId());
		for(Order order: orders) {
			List<Orderitem> orderitems = orderService.getOrderitemsById(order.getOrderId());
			order.setOrderitems(orderitems);
		}
		setOrdersInfo(orders);

		return SUCCESS;
	}
}
