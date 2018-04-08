package model.temp;


import java.util.ArrayList;
import java.util.Map;

public class Cart {
	private ArrayList<Orderitem> items; // bookid, amount
	
	public Cart() {
	}

	public Cart(ArrayList<Orderitem> items) {
		this.items = items;
	}
	
	public ArrayList<Orderitem> getItems() {
		return items;
	}
	
	public void setItems(ArrayList<Orderitem> items) {
		this.items = items;
	}
}
