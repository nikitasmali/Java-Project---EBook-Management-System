package com.demo.dao;

import java.util.List;

import com.demo.entity.BookDetails;

public interface BooksDao {

	public boolean addBooks(BookDetails bookDetails);

	public List<BookDetails> getAllBooks();

	public BookDetails getBookById(int id);

	public boolean updateEditBooks(BookDetails b);

	public boolean deleteBooks(int id);

	public List<BookDetails> getNewBook();

	public List<BookDetails> getRecentBook();

	public List<BookDetails> getOldBook();

	public List<BookDetails> getAllRecentBook();

	public List<BookDetails> getAllNewBook();

	public List<BookDetails> getAllOldBook();
	
	public List<BookDetails> getBookByOld(String email,String bookCategory);
	
	public boolean oldBookDelete(String email,String bookCategory,int bookId);
	
	public List<BookDetails> getBookBySearch(String ch);
}
