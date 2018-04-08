package dao.mysqlDao;

import java.util.List;

import model.mysql.Order;

public interface OrderDao {

	public Integer save(Order order);

	public void delete(Order order);

	public void update(Order order);

	public Order getOrderById(int id);
	
	public Order getLatestOrder();

	public List<Order> getOrdersByUID(int uid);
	
	public List<Order> getAllOrders();

}