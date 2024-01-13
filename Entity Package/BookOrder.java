package com.demo.entity;

public class BookOrder {
	private int id;
	private String orderId;
	private String userName;
	private String email;
	private String phno;
	private String fullAdd;
	private String bookName;
	private String author;
	private String price;
	private String paymentType;
	public BookOrder() {
	}

	public BookOrder(int id, String orderId, String userName, String email, String phno, String fullAdd,
			String bookName, String author, String price, String paymentType) {
		this.id = id;
		this.orderId = orderId;
		this.userName = userName;
		this.email = email;
		this.phno = phno;
		this.fullAdd = fullAdd;
		this.bookName = bookName;
		this.author = author;
		this.price = price;
		this.paymentType = paymentType;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	public String getFullAdd() {
		return fullAdd;
	}
	public void setFullAdd(String fullAdd) {
		this.fullAdd = fullAdd;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	@Override
	public String toString() {
		return "BookOrder [id=" + id + ", orderId=" + orderId + ", userName=" + userName + ", email=" + email
				+ ", phno=" + phno + ", fullAdd=" + fullAdd + ", bookName=" + bookName + ", author=" + author
				+ ", price=" + price + ", paymentType=" + paymentType + "]";
	}

	
	
}
