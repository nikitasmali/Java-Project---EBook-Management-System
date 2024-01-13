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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
			String name = request.getParameter("fname");
			String email = request.getParameter("email");
			String phno = request.getParameter("phno");
			String password = request.getParameter("password");
			String check = request.getParameter("check");
			
			//System.out.println(name + " " +email + " " +phno + " " +check );
		
			User user = new User();
			user.setName(name);
			user.setEmail(email);
			user.setPhno(phno);
			user.setPassword(password);
			
			HttpSession session = request.getSession();
			
			if(check != null)
			{
				UserDaoImpl daoImpl = new UserDaoImpl(DBUtil.getCon());
				boolean result2 = daoImpl.checkUser(email);
				if(result2)
				{
					boolean result = daoImpl.userRegister(user);
					
					if(result)
					{
						session.setAttribute("successmsg", "User Added Successfully...!");
						response.sendRedirect("register.jsp");
						//out.println("<h2 style=\"color: blue;\">User Added successfully...!</h2>");
					}
					else
					{
						session.setAttribute("failedmsg", "User Not Added...!");
						response.sendRedirect("register.jsp");
						//out.println("<h2 style=\"color: red;\">User Not Added...!</h2>");
					}
				}else
				{
					session.setAttribute("failedmsg", "User Already Exist. Try Another Email");
					response.sendRedirect("register.jsp");
				}
			}
			else
			{
				session.setAttribute("failedmsg", "Please Agree terms and conditions...");
				response.sendRedirect("register.jsp");
				//out.println("<h2 style=\"color: red;\">Please Click On Checkbox</h2>");
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
