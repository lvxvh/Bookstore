package model.mysql;

import java.lang.String;

public class Category {
	private int categoryId;
	private String name;

	public Category() {
	}

	public Category(String name) {
		this.name = name;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}