package action;

import java.util.ArrayList;

import java.util.Map;


import com.opensymphony.xwork2.ActionContext;


import model.mysql.User;
import model.temp.Cart;

import model.temp.Orderitem;
import service.CartService;

import service.OrderService;


public class ShoppingAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	private int bookId;
	private int amount;

	private CartService cartService;
	private OrderService orderService;

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

	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public String addToCart() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Cart cart = (Cart) session.get("cart");
		if (cart == null) {
			cart = new Cart(new ArrayList<Orderitem>());
		}
		cart = cartService.addBook(cart, bookId);
		session.put("cart", cart);
		return SUCCESS;
	}
	
	public String updateCart() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Cart cart = (Cart) session.get("cart");
		
		cart = cartService.updateAmount(cart, getBookId(), getAmount());
		
		
		return SUCCESS;
	}
	
	public String removeFromCart() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Cart cart = (Cart) session.get("cart");
		cart = cartService.removeBook(cart, getBookId());
		
		if (cart.getItems().size() == 0) {
			session.remove("cart");
		}
		
		return SUCCESS;
	}
	
	public String checkout() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		Cart cart = (Cart) session.get("cart");
		
		if (user == null) {
			this.addActionMessage("Login first!");
			return INPUT;
		}
		if (cart == null) {
			this.addActionError("The cart is empty！");
			return ERROR;
		}
		
		orderService.checkout(user, cart);
		session.remove("cart");
		return SUCCESS;
	}

	public int getTotalPrice(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Cart cart = (Cart) session.get("cart");
		return cartService.getTotalPrice(cart);
	}

	public ArrayList<Orderitem> getOrderitems()
	{
		Map<String, Object> session = ActionContext.getContext().getSession();
		Cart cart = (Cart) session.get("cart");
		return cartService.getOrderitems(cart);
	}
}
