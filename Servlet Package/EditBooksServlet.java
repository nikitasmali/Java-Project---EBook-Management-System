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
import com.demo.entity.BookDetails;

/**
 * Servlet implementation class EditBooksServlet
 */
@WebServlet("/editbooks")
public class EditBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditBooksServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			int id = Integer.parseInt(request.getParameter("id"));
			String bookName = request.getParameter("bname");
			String author = request.getParameter("author");
			String price = request.getParameter("price");
			String status = request.getParameter("status");
			
			BookDetails b = new BookDetails();
			b.setBookId(id);
			b.setBookName(bookName);
			b.setAuthor(author);
			b.setPrice(price);
			b.setStatus(status);
			
			BooksDaoImpl dao = new BooksDaoImpl(DBUtil.getCon());
			boolean result = dao.updateEditBooks(b);
			HttpSession session = request.getSession();
			
			if(result)
			{
				session.setAttribute("successmsg", "Book Update Successfully...!");
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

}
