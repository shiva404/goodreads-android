package se.janlindblom.android.goodreads.meta;

import java.util.Vector;

public class Shelf {
	private int count = 0;
	private String description = null;
	private int id = 0;
	private boolean exclusive = false;
	private String name = null;
	private Vector<Book> books = null;
	
	public String toString() {
		return "Id: " + id + ", Name: " + name + ", Count: " + count +
			", Exclusive: " + exclusive + ", Description: " + description;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	public int getCount() {
		return count;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setExclusive(boolean exclusive) {
		this.exclusive = exclusive;
	}
	public boolean isExclusive() {
		return exclusive;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
}
