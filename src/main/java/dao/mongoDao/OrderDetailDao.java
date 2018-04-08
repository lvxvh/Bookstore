package dao.mongoDao;

import java.util.List;

import model.mongo.OrderDetail;

public interface OrderDetailDao {

	public void save(OrderDetail orderDetail);

	public void delete(OrderDetail orderDetail);

	public void update(OrderDetail orderDetail);

	public OrderDetail getOrderDetailById(int orderId);

	public List<OrderDetail> getAllOrderDetails();
}