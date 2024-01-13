package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.demo.entity.User;

public class UserDaoImpl implements UserDao
{
	private Connection con;
	
	public UserDaoImpl(Connection con)
	{
		this.con = con;
	}
	
	@Override
	public boolean userRegister(User user) 
	{
		boolean result = false;
		String query = "insert into bookuser(name,email,phno,password) values(?, ?, ?, ?)";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPhno());
			ps.setString(4, user.getPassword());
			int i = ps.executeUpdate();	
		if(i == 1)
		{
			result = true;
		}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public User login(String email, String password)
	{
		User user = null;
		try
		{
			String query = "select * from bookuser where email=? and password=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setPhno(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.setAddress(rs.getString(6));
				user.setLandmark(rs.getString(7));
				user.setCity(rs.getString(8));
				user.setState(rs.getString(9));
				user.setPincode(rs.getString(10));
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean checkPassword(int id,String password) {
		boolean result = false;
		try {
			String query = "select * from bookuser where id=? and password=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean updateProfile(User user) {

		boolean result = false;
		
		try {
			String query = "update bookuser set name=?,email=?,phno=? where id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPhno());
			ps.setInt(4, user.getId());
			int i = ps.executeUpdate();	
		if(i == 1)
		{
			result = true;
		}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean checkUser(String email) {
		boolean result = true;
		try {
			String query = "select * from bookuser where email=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,email);
			ResultSet rs= ps.executeQuery();
			while(rs.next())
			{
				result = false;
			}	
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}
}
