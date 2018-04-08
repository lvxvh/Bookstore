package service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.mongo.OrderDetail;
import model.mysql.Book;
import model.mysql.Order;
import model.mysql.User;
import model.temp.Orderitem;
import model.temp.Cart;

public interface CartService {

    public Cart addBook(Cart cart, int bookId);

    public Cart updateAmount(Cart cart, int bookId, int amount);

    public Cart removeBook(Cart cart, int bookId);

    public int getTotalPrice(Cart cart);

    public ArrayList<Orderitem> getOrderitems(Cart cart);


}