package com.demo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.dao.DBUtil;
import com.demo.dao.UserDaoImpl;
import com.demo.entity.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
			
			UserDaoImpl daoImpl = new UserDaoImpl(DBUtil.getCon());
			HttpSession session = request.getSession();
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			if("admin@gmail.com".equals(email) && "admin".equals(password))
			{
				User user = new User();
				user.setName("Admin");
				session.setAttribute("userobject", user);
				response.sendRedirect("admin/home.jsp");
			}
			else
			{
				User user = daoImpl.login(email, password);
				if(user != null)
				{
					session.setAttribute("userobject", user);
					response.sendRedirect("index.jsp");
				}
				else
				{
					session.setAttribute("failedmsg", "Email & Password Is Invalid");
					response.sendRedirect("login.jsp");
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
