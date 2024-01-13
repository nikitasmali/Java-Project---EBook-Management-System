package com.demo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.dao.BooksDaoImpl;
import com.demo.dao.DBUtil;

/**
 * Servlet implementation class DeleteOldBookServlet
 */
@WebServlet("/delete_old_book")
public class DeleteOldBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteOldBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email = request.getParameter("email");
			int bookId = Integer.parseInt(request.getParameter("bookId"));
			BooksDaoImpl dao = new BooksDaoImpl(DBUtil.getCon());
			boolean result =  dao.oldBookDelete(email,"Old",bookId);
			
			 HttpSession session = request.getSession();
			 if(result)
			 {
				 session.setAttribute("sucMsg", "Old Book Deleted Successfully");
				 response.sendRedirect("old_book.jsp");
			 }
			 else
			 {
				 session.setAttribute("sucMsg", "Something went wrong");
				 response.sendRedirect("old_book.jsp");
			 }
		} 
		catch (Exception e)
		{
			e.printStackTrace();
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
