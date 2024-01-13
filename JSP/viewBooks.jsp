<%@page import="com.demo.entity.BookDetails"%>
<%@page import="com.demo.dao.DBUtil"%>
<%@page import="com.demo.dao.BooksDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="all_component/allCss.jsp"%>
</head>
<body style="background-color: #f0f1f2;">
	<%@include file="all_component/navbar.jsp"%>

	<%
	int bookId = Integer.parseInt(request.getParameter("bookId"));
	BooksDaoImpl dao = new BooksDaoImpl(DBUtil.getCon());
	BookDetails bookDetails = dao.getBookById(bookId);
	%>

	<div class="container p-3">
		<div class="row">
			<div class="col-md-6 text-center p-5 border bg-white">
				<img src="book/<%=bookDetails.getPhoto() %>" style="height: 150px; width: 100px"><br>
				<h4 class="mt-3">
					Book Name: <span class="text-success"><%=bookDetails.getBookName() %></span>
				</h4>
				<h4>
 					Author Name: <span class="text-success"><%=bookDetails.getAuthor() %></span>
				</h4>
				<h4>
					Category : <span class="text-success"><%=bookDetails.getBookCategory() %></span>
				</h4>
			</div>
			<div class="col-md-6 text-center p-5 border bg-white">
				<h2><%=bookDetails.getBookName() %></h2>
				
				<%
				if("Old".equals(bookDetails.getBookCategory()))
				{%>
					<h5 class="text-primary">Contact To Seller</h5>
					<h5 class="text-primary"><i class="fa-solid fa-envelope"></i> Email: <%=bookDetails.getUser_email() %> </h5>
				<%	
				}
				%>
				
				<div class="row">
					<div class="col-md-4 text-danger text-center p-2">
						<i class="fa-solid fa-money-bill-wave fa-2x"></i>
						<p>Cash On Delivery</p>
					</div>
					<div class="col-md-4 text-danger text-center p-2">
						<i class="fa-solid fa-rotate-left fa-2x"></i>
						<p>Return Available</p>
					</div>
					<div class="col-md-4 text-danger text-center p-2">
						<i class="fa-solid fa-truck fa-2x"></i>
						<p>Free Delivery</p>
					</div>

				</div>
				
				<%
				if("Old".equals(bookDetails.getBookCategory()))
				{%>
					<div class="text-center p-3">
					<a href="index.jsp" class="btn btn-success">
					<i class="fas fa-cart-plus"></i> Continue Shopping</a> <a href=""
						class="btn btn-danger"><i class="fas fa-rupee-sign"></i> <%=bookDetails.getPrice() %></a>
				</div>
				<%}
				else 
				{%>
					<div class="text-center p-3">
					<a href="" class="btn btn-primary"><i class="fas fa-cart-plus"></i> Add To Cart</a> <a href=""
						class="btn btn-danger"><i class="fas fa-rupee-sign"></i> <%=bookDetails.getPrice() %></a>
				</div>
				<%}
				%>
				
				
			</div>
		</div>
	</div>
</body>
</html>