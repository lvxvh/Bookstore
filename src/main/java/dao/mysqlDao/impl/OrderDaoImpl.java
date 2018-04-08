package dao.mysqlDao.impl;

import java.util.List;

import model.mysql.Order;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import dao.mysqlDao.OrderDao;

public class OrderDaoImpl extends HibernateDaoSupport implements OrderDao {

	public Integer save(Order order) {
		getHibernateTemplate().save(order);
		@SuppressWarnings("unchecked")
		List<Order> orders = (List<Order>) getHibernateTemplate().find(
				"from Order as o where o.orderId=(select max(orderId) from Order)");
		return orders.size() > 0 ? orders.get(0).getOrderId() : -1;
	}

	public void delete(Order order) {
		getHibernateTemplate().delete(order);
	}

	public void update(Order order) {
		getHibernateTemplate().merge(order);
	}

	public Order getOrderById(int id) {
		@SuppressWarnings("unchecked")
		List<Order> orders = (List<Order>) getHibernateTemplate().find(
				"from Order as o where o.orderId=?", id);
		return orders.size() > 0 ? orders.get(0) : null;
	}

	public Order getLatestOrder() {
		@SuppressWarnings("unchecked")
		List<Order> orders = (List<Order>) getHibernateTemplate().find(
				"from Order as o where o.orderId=(select max(orderId) from Order)");
		Order order = orders.size() > 0 ? orders.get(0) : null;
		return order;
	}
	
	public List<Order> getOrdersByUID(int uid) {
		@SuppressWarnings("unchecked")
		List<Order> orders = (List<Order>) getHibernateTemplate().find("from Order as o where o.userId=?", uid);
		return orders;
	}
	
	public List<Order> getAllOrders() {
		@SuppressWarnings("unchecked")
		List<Order> orders = (List<Order>) getHibernateTemplate().find(
				"from Order");
		return orders;
	}

}
