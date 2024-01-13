package com.demo.dao;

import java.util.List;

import com.demo.entity.BookOrder;

public interface BookOrderDao
{
	
	public boolean saveOrder(List<BookOrder> bOrder);
	
	public List<BookOrder> getBook(String email);
	
	public List<BookOrder> getAllBookOrders();
}
