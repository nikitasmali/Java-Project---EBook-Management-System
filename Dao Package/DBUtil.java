package com.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil 
{
	public static Connection getCon()
	{
		Connection con=null;
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String url="jdbc:oracle:thin:@DESKTOP-MD5FH4D:1521:XE";
				con = DriverManager.getConnection(url,"hr","hr");
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			return con;
		} 	
}

