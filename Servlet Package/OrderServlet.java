package com.demo.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.dao.BookOrderDaoImpl;
import com.demo.dao.CartDaoImpl;
import com.demo.dao.DBUtil;
import com.demo.entity.BookOrder;
import com.demo.entity.Cart;

/**
 * Servlet implementation class OrderServlet
 */

public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
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
			HttpSession session = request.getSession();
			int id = Integer.parseInt(request.getParameter("id"));
			
			String name = request.getParameter("userName");
			String email = request.getParameter("email");
			String phno = request.getParameter("phno");
			String address = request.getParameter("address");
			String landmark = request.getParameter("landmark");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String pincode = request.getParameter("pincode");
			String paymentType = request.getParameter("paymentType");
			
			String fullAdd = address + "," + landmark + "," + city + "," + state + "," + pincode;
			
			CartDaoImpl dao = new CartDaoImpl(DBUtil.getCon());
			
			List<Cart> blist = dao.getBookByUser(id);
			if(blist.isEmpty())
			{
				session.setAttribute("failedMsg", "Add Item To Cart..");
				response.sendRedirect("checkout.jsp");
			}else
			{
				BookOrderDaoImpl dao2 = new BookOrderDaoImpl(DBUtil.getCon());
				BookOrder order = null;
				
				ArrayList<BookOrder> orderList = new ArrayList<BookOrder>();
				Random r = new Random();
				for(Cart c:blist)
				{
					order = new BookOrder();
					order.setOrderId("BOOK-ORD-00"+r.nextInt(1000));
					order.setUserName(name);
					order.setEmail(email);
					order.setPhno(phno);
					order.setFullAdd(fullAdd);
					order.setBookName(c.getBookName());
					order.setAuthor(c.getAuthor());
					order.setPrice(c.getPrice()+"");
					order.setPaymentType(paymentType);
					orderList.add(order);
				}
				
				if("noselect".equals(paymentType))
				{
					session.setAttribute("failedMsg", "Select Payment Method..");
					response.sendRedirect("checkout.jsp");
				}
				else
				{
					boolean result = dao2.saveOrder(orderList);
					if (result) 
					{
						response.sendRedirect("order_success.jsp");
					}
					else
					{
						session.setAttribute("failedMsg", "Order Failed");
						response.sendRedirect("checkout.jsp");
					}
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
