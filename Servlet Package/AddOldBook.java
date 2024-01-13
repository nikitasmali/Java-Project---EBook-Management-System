package com.demo.servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.demo.dao.BooksDaoImpl;
import com.demo.dao.DBUtil;
import com.demo.entity.BookDetails;

/**
 * Servlet implementation class AddOldBook
 */
@WebServlet("/add_old_book")
@MultipartConfig
public class AddOldBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddOldBook() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String bookName = request.getParameter("bname");
			String author = request.getParameter("author");
			String price = request.getParameter("price");
			String bookCategory ="Old";
			String status ="Active";
			Part part = request.getPart("bimg");
			String fileName = part.getSubmittedFileName();

			String userEmail = request.getParameter("user");

			BookDetails bookDetails = new BookDetails(bookName, author, price, bookCategory, status, fileName, userEmail);
			BooksDaoImpl booksDaoImpl = new BooksDaoImpl(DBUtil.getCon());

			boolean result = booksDaoImpl.addBooks(bookDetails);

			HttpSession session = request.getSession();
			if (result) {
				String path = getServletContext().getRealPath("") + "book";
				File file = new File(path);
				part.write(path + File.separator + fileName);
				session.setAttribute("successmsg", "Book Added Successfully..!");
				response.sendRedirect("sellBook.jsp");
			} else {
				session.setAttribute("failedmsg", "Something went wrong..!");
				response.sendRedirect("sellBook.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
