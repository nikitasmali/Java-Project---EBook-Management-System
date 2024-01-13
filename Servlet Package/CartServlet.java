package com.demo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.dao.BooksDaoImpl;
import com.demo.dao.CartDaoImpl;
import com.demo.dao.DBUtil;
import com.demo.entity.BookDetails;
import com.demo.entity.Cart;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int bookId = Integer.parseInt(request.getParameter("bookId"));
			int userId = Integer.parseInt(request.getParameter("userId"));
			
			BooksDaoImpl dao = new BooksDaoImpl(DBUtil.getCon());
			BookDetails b = dao.getBookById(bookId);
			
			Cart c = new Cart();
			c.setBookId(bookId);
			c.setUserId(userId);
			c.setBookName(b.getBookName());
			c.setAuthor(b.getAuthor());
			c.setPrice(Double.parseDouble(b.getPrice()));
			c.setTotalPrice(Double.parseDouble(b.getPrice()));
			
			CartDaoImpl dao2 = new CartDaoImpl(DBUtil.getCon());
			boolean result = dao2.addCart(c);
			
			HttpSession session = request.getSession();
			
			if(result)
			{
				session.setAttribute("addCart", "Book Added To cart");
				response.sendRedirect("ViewNewBook.jsp");
			}
			else
			{
				session.setAttribute("failed", "Something Went Wrong");
				response.sendRedirect("ViewNewBook.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
