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
 * Servlet implementation class UpdateProfileServlet
 */
@WebServlet("/update_profile")
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfileServlet() {
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
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("fname");
			String email = request.getParameter("email");
			String phno = request.getParameter("phno");
			String password = request.getParameter("password");
			
			User user = new User();
			user.setId(id);
			user.setName(name);
			user.setEmail(email);
			user.setPhno(phno);
			
			HttpSession session = request.getSession();
			UserDaoImpl dao = new UserDaoImpl(DBUtil.getCon());
			boolean result = dao.checkPassword(id, password);
			if(result)
			{
				boolean result1 = dao.updateProfile(user);
				if(result1)
				{
					session.setAttribute("successmsg", "Profile Updated Successfully...!");
					response.sendRedirect("editProfile.jsp");
				}else {
					session.setAttribute("failedmsg", "Something went wrong");
					response.sendRedirect("editProfile.jsp");
				}
			}
			else
			{
				session.setAttribute("failedmsg", "Password Is Incorrect");
				response.sendRedirect("editProfile.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
