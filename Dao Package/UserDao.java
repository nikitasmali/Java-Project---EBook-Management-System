package com.demo.dao;

import com.demo.entity.User;

public interface UserDao 
{
	public boolean userRegister(User user);
	
	public User login(String email,String password);
	
	public boolean checkPassword(int id,String password);
	
	public boolean updateProfile(User user);
	
	public boolean checkUser(String email);
}
