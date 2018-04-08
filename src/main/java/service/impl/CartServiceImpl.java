package service.impl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import dao.mysqlDao.BookDao;
import model.mongo.OrderDetail;
import model.mysql.Book;
import model.mysql.Order;
import model.mysql.User;
import model.temp.Cart;
import model.temp.Orderitem;
import service.CartService;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by lxh on 2017/7/5.
 */
public class CartServiceImpl implements CartService {
    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public Cart addBook(Cart cart, int bookId) {
        ArrayList<Orderitem> items = cart.getItems();
        boolean existed = false;
        Iterator<Orderitem> it = items.iterator();
        while(it.hasNext()) { //existed
            Orderitem item = it.next();
            if(item.getBookId() == bookId) {
                item.setAmount(item.getAmount() + 1);
                it.remove();
                items.add(item);
                existed = true;
                break;
            }
        }

        if(!existed) {  //new
            int unitPrice = bookDao.getBookById(bookId).getPrice();
            String title = bookDao.getBookById(bookId).getTitle();
            Orderitem orderitem = new Orderitem(bookId, title, 1, unitPrice);
            items.add(orderitem);
        }
        cart.setItems(items);
        return cart;
    }

    public Cart updateAmount(Cart cart, int bookId, int amount) {
        ArrayList<Orderitem> items = cart.getItems();
        if (amount <= 0) return cart;
        for(int i = 0;i < items.size();i++){
            Orderitem item = items.get(i);
            if(item.getBookId() == bookId) {
                item.setAmount(amount);
                items.set(i, item);
                break;
            }
        }
        cart.setItems(items);
        return cart;
    }

    public Cart removeBook(Cart cart, int bookId) {
        ArrayList<Orderitem> items = cart.getItems();
        Iterator<Orderitem> it = items.iterator();
        while(it.hasNext()) {
            Orderitem item = it.next();
            if(item.getBookId() == bookId) {
                it.remove();
                break;
            }
        }
        cart.setItems(items);
        return cart;
    }

    public int getTotalPrice(Cart cart){
        ArrayList<Orderitem> items = cart.getItems();
        int totalPrice = 0;
        Iterator<Orderitem> it = items.iterator();
        while(it.hasNext()) {
            Orderitem item = it.next();
            totalPrice += bookDao.getBookById(item.getBookId()).getPrice();
        }
        return totalPrice;
    }

    public ArrayList<Orderitem> getOrderitems(Cart cart)
    {
        return cart.getItems();

    }

}
