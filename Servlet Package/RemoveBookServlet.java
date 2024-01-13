package com.demo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.dao.CartDaoImpl;
import com.demo.dao.DBUtil;

/**
 * Servlet implementation class RemoveBookServlet
 */
@WebServlet("/remove_book")
public class RemoveBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		int cartId = Integer.parseInt(request.getParameter("cartId"));
		CartDaoImpl dao = new CartDaoImpl(DBUtil.getCon());
		 boolean result = dao.deleteBook(bookId,userId,cartId);
		 
		 HttpSession session = request.getSession();
		 if(result)
		 {
			 session.setAttribute("sucMsg", "Book Removed From Cart");
			 response.sendRedirect("checkout.jsp");
		 }
		 else
		 {
			 session.setAttribute("failedMsg", "Something went wrong");
			 response.sendRedirect("checkout.jsp");
		 }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
