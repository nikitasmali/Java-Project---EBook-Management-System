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
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			BooksDaoImpl dao = new BooksDaoImpl(DBUtil.getCon());
			boolean result = dao.deleteBooks(id);
			
			HttpSession session = request.getSession();
			if(result)
			{
				session.setAttribute("successmsg", "Book Deleted Successfully...!");
				response.sendRedirect("admin/allBooks.jsp");
			}
			else
			{
				session.setAttribute("failedmsg", "Something Wrong On Server");
				response.sendRedirect("admin/allBooks.jsp");
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
