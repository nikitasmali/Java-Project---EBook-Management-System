package com.demo.dao;

import java.util.List;

import com.demo.entity.Cart;

public interface CartDao
{
	public boolean addCart(Cart c); 
	
	public  List<Cart> getBookByUser(int userId);
	
	public boolean deleteBook(int bookId,int userId,int cartId);
	
}
