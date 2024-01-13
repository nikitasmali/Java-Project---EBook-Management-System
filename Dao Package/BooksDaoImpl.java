package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.demo.entity.BookDetails;

public class BooksDaoImpl implements BooksDao
{
	private Connection con;
	
	public BooksDaoImpl(Connection con) 
	{
		super();
		this.con = con;
	}

	@Override
	public boolean addBooks(BookDetails bookDetails) {
		boolean result = false;
		
		try 
		{
			String query = "insert into bookdetails(bookName, author,"
					+ " price, bookCategory, status, photo, user_email) "
					+ "values(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, bookDetails.getBookName());
			ps.setString(2, bookDetails.getAuthor());
			ps.setString(3, bookDetails.getPrice());
			ps.setString(4, bookDetails.getBookCategory());
			ps.setString(5, bookDetails.getStatus());
			ps.setString(6, bookDetails.getPhoto());
			ps.setString(7, bookDetails.getUser_email());
			
			int i = ps.executeUpdate();
			
			if(i == 1)
			{
				result = true;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<BookDetails> getAllBooks()
	{
		List<BookDetails> list = new ArrayList<BookDetails>();
		BookDetails bookDetails = null;
		try 
		{
			String query = "select * from bookdetails";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				bookDetails = new BookDetails();
				bookDetails.setBookId(rs.getInt(1));
				bookDetails.setBookName(rs.getString(2));
				bookDetails.setAuthor(rs.getString(3));
				bookDetails.setPrice(rs.getString(4));
				bookDetails.setBookCategory(rs.getString(5));
				bookDetails.setStatus(rs.getString(6));
				bookDetails.setPhoto(rs.getString(7));
				bookDetails.setUser_email(rs.getString(8));
			
				list.add(bookDetails);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public BookDetails getBookById(int id)
	{
		BookDetails bookDetails = null;
		try 
		{
			String query  = "select * from bookdetails where bookId=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				bookDetails = new BookDetails();
				bookDetails.setBookId(rs.getInt(1));
				bookDetails.setBookName(rs.getString(2));
				bookDetails.setAuthor(rs.getString(3));
				bookDetails.setPrice(rs.getString(4));
				bookDetails.setBookCategory(rs.getString(5));
				bookDetails.setStatus(rs.getString(6));
				bookDetails.setPhoto(rs.getString(7));
				bookDetails.setUser_email(rs.getString(8));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return bookDetails;
	}

	@Override
	public boolean updateEditBooks(BookDetails b) {
		boolean result = false;
		try {
			String query = "update bookdetails set bookName=?,author=?,price=?,status=? where bookId=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, b.getBookName());
			ps.setString(2, b.getAuthor());
			ps.setString(3, b.getPrice());
			ps.setString(4, b.getStatus());
			ps.setInt(5, b.getBookId());
			
			int i = ps.executeUpdate();
			if(i == 1)
			{
				result = true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean deleteBooks(int id) 
	{	
		boolean result = false;
		try 
		{
			String query = "delete from bookdetails where bookId=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			int i = ps.executeUpdate();
			if(i == 1)
			{
				result = true;
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<BookDetails> getNewBook() 
	{
		List<BookDetails> list = new ArrayList<BookDetails>();
		BookDetails b = null;
		
		try 
		{
			String query = "select * from bookdetails where bookCategory=? and status=? order by bookId DESC";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, "New");
			ps.setString(2, "Active");
			ResultSet rs = ps.executeQuery();
			int i = 1;
			while(rs.next() && i<=4)
			{
				b = new BookDetails();
				b.setBookId(rs.getInt(1));
				b.setBookName(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhoto(rs.getString(7));
				b.setUser_email(rs.getString(8));
				list.add(b);
				i++;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<BookDetails> getRecentBook()
	{
		List<BookDetails> list = new ArrayList<BookDetails>();
		BookDetails b = null;
		
		try 
		{
			String query = "select * from bookdetails where status=? order by bookId DESC";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, "Active");
			ResultSet rs = ps.executeQuery();
			int i = 1;
			while(rs.next() && i<=4)
			{
				b = new BookDetails();
				b.setBookId(rs.getInt(1));
				b.setBookName(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhoto(rs.getString(7));
				b.setUser_email(rs.getString(8));
				list.add(b);
				i++;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<BookDetails> getOldBook() {
		
		List<BookDetails> list = new ArrayList<BookDetails>();
		BookDetails b = null;
		
		try 
		{
			String query = "select * from bookdetails where bookCategory=? and status=? order by bookId DESC";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, "Old");
			ps.setString(2, "Active");
			ResultSet rs = ps.executeQuery();
			int i = 1;
			while(rs.next() && i<=4)
			{
				b = new BookDetails();
				b.setBookId(rs.getInt(1));
				b.setBookName(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhoto(rs.getString(7));
				b.setUser_email(rs.getString(8));
				list.add(b);
				i++;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<BookDetails> getAllRecentBook()
	{
		List<BookDetails> list = new ArrayList<BookDetails>();
		BookDetails b = null;
		
		try 
		{
			String query = "select * from bookdetails where status=? order by bookId DESC";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, "Active");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				b = new BookDetails();
				b.setBookId(rs.getInt(1));
				b.setBookName(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhoto(rs.getString(7));
				b.setUser_email(rs.getString(8));
				list.add(b);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<BookDetails> getAllNewBook() 
	{
		List<BookDetails> list = new ArrayList<BookDetails>();
		BookDetails b = null;
		
		try 
		{
			String query = "select * from bookdetails where bookCategory=? and status=? order by bookId DESC";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, "New");
			ps.setString(2, "Active");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				b = new BookDetails();
				b.setBookId(rs.getInt(1));
				b.setBookName(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhoto(rs.getString(7));
				b.setUser_email(rs.getString(8));
				list.add(b);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<BookDetails> getAllOldBook() 
	{
		

		List<BookDetails> list = new ArrayList<BookDetails>();
		BookDetails b = null;
		
		try 
		{
			String query = "select * from bookdetails where bookCategory=? and status=? order by bookId DESC";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, "Old");
			ps.setString(2, "Active");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				b = new BookDetails();
				b.setBookId(rs.getInt(1));
				b.setBookName(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhoto(rs.getString(7));
				b.setUser_email(rs.getString(8));
				list.add(b);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<BookDetails> getBookByOld(String email, String bookCategory) {
		List<BookDetails> list = new ArrayList<BookDetails>();
		BookDetails b = null;
		try {
			String query = "select * from bookdetails where bookCategory=? and user_email=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, bookCategory);
			ps.setString(2, email);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				b = new BookDetails();
				b.setBookId(rs.getInt(1));
				b.setBookName(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhoto(rs.getString(7));
				b.setUser_email(rs.getString(8));
				list.add(b);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean oldBookDelete(String email, String bookCategory,int bookId) {
		boolean result = false;
		
		try {
			String query = "delete from bookdetails where bookCategory=? and user_email=? and bookId=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, bookCategory);
			ps.setString(2, email);
			ps.setInt(3, bookId);
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
	public List<BookDetails> getBookBySearch(String ch) {
		
		List<BookDetails> list = new ArrayList<BookDetails>();
		BookDetails b = null;
		try {
			String query = "select * from bookdetails where bookName like ? or author like ? or bookCategory like ? and status=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, "%"+ch+"%");
			ps.setString(2, "%"+ch+"%");
			ps.setString(3, "%"+ch+"%");
			ps.setString(4, "Active");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				b = new BookDetails();
				b.setBookId(rs.getInt(1));
				b.setBookName(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhoto(rs.getString(7));
				b.setUser_email(rs.getString(8));
				list.add(b);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
}




