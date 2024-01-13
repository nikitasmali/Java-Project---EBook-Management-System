package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.demo.entity.BookOrder;

public class BookOrderDaoImpl implements BookOrderDao{
	private Connection con;

	public BookOrderDaoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public boolean saveOrder(List<BookOrder> bOrder) {
		boolean result = false;
		try {
			String query = "insert into bookorders (orderId,userName,email,address,phone,bookName,author,price,payment) values(?,?,?,?,?,?,?,?,?)";
			
			con.setAutoCommit(false);
			PreparedStatement ps= con.prepareStatement(query);
			
			for(BookOrder b:bOrder)
			{
				ps.setString(1, b.getOrderId());
				ps.setString(2, b.getUserName());
				ps.setString(3, b.getEmail());
				ps.setString(4, b.getFullAdd());
				ps.setString(5, b.getPhno());
				ps.setString(6, b.getBookName());
				ps.setString(7, b.getAuthor());
				ps.setString(8, b.getPrice());
				ps.setString(9, b.getPaymentType());
				ps.addBatch();
			}
			int[] count = ps.executeBatch();
			con.commit();
			result = true;
			con.setAutoCommit(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<BookOrder> getBook(String email) {
		List<BookOrder> list = new ArrayList<BookOrder>();
		BookOrder order = null;
		
		try {
			 String query = "select * from bookorders where email=?";
			 PreparedStatement ps = con.prepareStatement(query);
			 ps.setString(1, email);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next())
			 {
				 order = new BookOrder();
				 order.setId(rs.getInt(1));
				 order.setOrderId(rs.getString(2));
				 order.setUserName(rs.getString(3));
				 order.setEmail(rs.getString(4));
				 order.setFullAdd(rs.getString(5));
				 order.setPhno(rs.getString(6));
				 order.setBookName(rs.getString(7));
				 order.setAuthor(rs.getString(8));
				 order.setPrice(rs.getString(9));
				 order.setPaymentType(rs.getString(10));
				 list.add(order);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BookOrder> getAllBookOrders() {
		List<BookOrder> list = new ArrayList<BookOrder>();
		BookOrder order = null;
		
		try {
			 String query = "select * from bookorders";
			 PreparedStatement ps = con.prepareStatement(query);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next())
			 {
				 order = new BookOrder();
				 order.setId(rs.getInt(1));
				 order.setOrderId(rs.getString(2));
				 order.setUserName(rs.getString(3));
				 order.setEmail(rs.getString(4));
				 order.setFullAdd(rs.getString(5));
				 order.setPhno(rs.getString(6));
				 order.setBookName(rs.getString(7));
				 order.setAuthor(rs.getString(8));
				 order.setPrice(rs.getString(9));
				 order.setPaymentType(rs.getString(10));
				 list.add(order);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}	
}
