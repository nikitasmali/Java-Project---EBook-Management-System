package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.demo.entity.BookDetails;
import com.demo.entity.Cart;

public class CartDaoImpl implements CartDao {

	private Connection con;

	public CartDaoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public boolean addCart(Cart c) {
		boolean result = false;
		try {
			String query = "insert into cart(bookId,userId,bookName,author,price,totalPrice) values(?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, c.getBookId());
			ps.setInt(2, c.getUserId());
			ps.setString(3, c.getBookName());
			ps.setString(4, c.getAuthor());
			ps.setDouble(5, c.getPrice());
			ps.setDouble(6, c.getTotalPrice());
			
			int i = ps.executeUpdate();
			if(i == 1)
			{
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Cart> getBookByUser(int userId) {
		List<Cart> list = new ArrayList<Cart>();
		Cart c = null;
		double totalPrice = 0;
		try {
			String query = "select * from cart where userId=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, userId);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				c = new Cart();
				c.setCartId(rs.getInt(1));
				c.setBookId(rs.getInt(2));
				c.setUserId(rs.getInt(3));
				c.setBookName(rs.getString(4));
				c.setAuthor(rs.getString(5));
				c.setPrice(rs.getDouble(6));
				
				totalPrice = totalPrice + rs.getDouble(7);
				c.setTotalPrice(totalPrice);
				
				list.add(c);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean deleteBook(int bookId,int userId,int cartId) {
		boolean result = false;
		
		try {
			String query = "delete from cart where bookId=? and userId=? and cartId=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, bookId);
			ps.setInt(2, userId);
			ps.setInt(3, cartId);
			int i =	ps.executeUpdate();
		if(i == 1)
		{
			result = true;
		}
		}
		catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}
}
