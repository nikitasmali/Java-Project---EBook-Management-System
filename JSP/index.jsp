<%@page import="com.demo.entity.User"%>
<%@page import="com.demo.entity.BookDetails"%>
<%@page import="java.util.List"%>
<%@page import="com.demo.dao.BooksDaoImpl"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.demo.dao.DBUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EBook Management System</title>
<%@include file="all_component/allCss.jsp"%>
<style type="text/css">
.back-img {
	background: url("img/background.jpg");
	height: 50vh;
	width: 100x;
	background-repeat: no-repeat;
}

.crd-ho:hover {
	background-color: #f2e4e4;
}
</style>
</head>
<body style="background-color: #f7f7f7;">

	<%
		User user=(User)session.getAttribute("userobject"); 
	%>


	<%@include file="all_component/navbar.jsp"%>
	<div class="container-fluid back-img">
		<h2 class="text-center" style="color: #f9fbe7;">EBook Management
			System</h2>
	</div>


	<!-- Start Recent Book Section -->

	<div class="container">
		<h3 class="text-center">Recent Book</h3>
		<div class="row">
			<%
			BooksDaoImpl dao2 = new BooksDaoImpl(DBUtil.getCon());
			List<BookDetails> list = dao2.getRecentBook();
			for (BookDetails b : list) {
			%>
			<div class="col-md-3">
				<div class="card crd-ho">
					<div class="card-body text-center">
						<img alt="" src="book/<%=b.getPhoto()%>"
							style="width: 150px; height: 200px;" class="img-thumblin">
						<p><%=b.getBookName()%></p>
						<p><%=b.getAuthor()%></p>
						<p>
						
						<%
						if(b.getBookCategory().equals("Old"))
						{%>
						Category:<%=b.getBookCategory()%></p>
							<div class="row">					
							<a href="viewBooks.jsp?bookId=<%=b.getBookId() %>"
								class="btn btn-success btn-sm ml-5">Contact & Details</a>
							<a href="" class="btn btn-danger btn-sm ml-1"><%=b.getPrice()%></a>
						</div>
						<%
						}
						else
						{%>
							Categories :<%=b.getBookCategory()%></p>
						<div class="row">
					
						<%
						if(user == null)
						{
						%>
							<a href="login.jsp" class="btn btn-danger btn-sm ml-3"><i
								class="fa-solid fa-cart-shopping"></i> Add To Cart</a> 
						<%}
						else
						{%>
						 	<a href="cart?bookId=<%=b.getBookId() %>&&userId=<%=user.getId()%>" class="btn btn-danger btn-sm ml-2"><i
								class="fa-solid fa-cart-shopping"></i> Add To Cart</a> 
						<%}
						%>
							<a href="viewBooks.jsp?bookId=<%=b.getBookId() %>" class="btn btn-success btn-sm ml-1">Details</a> 
							<a href="" class="btn btn-danger btn-sm ml-1"><%=b.getPrice()%></a>
						</div>
						<% 	
						}
						%>
						
					</div>
				</div>
			</div>
			<%
			}
			%>


		</div>
		<div class="text-center mt-1">
			<a href="ViewRecentBook.jsp" class="btn btn-danger btn-sm">View All</a>
		</div>
	</div>
	<!-- End Recent Book Section -->

	<hr>

	<!-- Start New Book Section -->

	<div class="container">
		<h3 class="text-center">New Book</h3>
		<div class="row">


			<%
			BooksDaoImpl dao = new BooksDaoImpl(DBUtil.getCon());
			List<BookDetails> list1 = dao.getNewBook();
			for (BookDetails b : list1) {
			%>
			<div class="col-md-3">
				<div class="card crd-ho">
					<div class="card-body text-center">
						<img alt="" src="book/<%=b.getPhoto()%>"
							style="width: 150px; height: 200px;" class="img-thumblin">
						<p><%=b.getBookName()%></p>
						<p><%=b.getAuthor()%></p>
						<p>Category: <%=b.getBookCategory()%></p>
						<div class="row">
						
						<%
						if(user == null)
						{
						%>
							<a href="login.jsp" class="btn btn-danger btn-sm ml-2"><i
								class="fa-solid fa-cart-shopping"></i> Add To Cart</a> 
						<%}
						else
						{%>
						 	<a href="cart?bookId=<%=b.getBookId() %>&&userId=<%=user.getId()%>" class="btn btn-danger btn-sm ml-2"><i
								class="fa-solid fa-cart-shopping"></i> Add To Cart</a> 
						<%}
						%>
							<a href="viewBooks.jsp?bookId=<%=b.getBookId() %>"
								class="btn btn-success btn-sm ml-1">Details</a> 
							<a href="" class="btn btn-danger btn-sm ml-1"><%=b.getPrice()%></a>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			%>
			
		</div>
		<div class="text-center mt-1">
			<a href="ViewNewBook.jsp" class="btn btn-danger btn-sm">View All</a>
		</div>
	</div>
	<!-- End New Book Section -->

	<hr>

	<!-- Start Old Book Section -->

	<div class="container">
		<h3 class="text-center">Old Book</h3>
		<div class="row">
		<%
			BooksDaoImpl dao3 = new BooksDaoImpl(DBUtil.getCon());
			List<BookDetails> list3 = dao3.getOldBook();
			for (BookDetails b : list3) {
			%>
			<div class="col-md-3">
				<div class="card crd-ho">
					<div class="card-body text-center">
						<img alt="" src="book/<%=b.getPhoto()%>"
							style="width: 150px; height: 200px;" class="img-thumblin">
						<p><%=b.getBookName()%></p>
						<p><%=b.getAuthor()%></p>
						<p>Category:<%=b.getBookCategory() %> </p>
						<div class="row">
						 <a href="viewBooks.jsp?bookId=<%=b.getBookId() %>"
								class="btn btn-success btn-sm ml-5">Contact & Details</a> <a href=""
								class="btn btn-danger btn-sm ml-1"><%=b.getPrice()%></a>
						</div>
						
					</div>
				</div>
			</div>
			<%
			}
			%>

		</div>
		<div class="text-center mt-1">
			<a href="ViewOldBook.jsp" class="btn btn-danger btn-sm">View All</a>
		</div>
	</div>
	<!-- End Old Book Section -->
	<%@include file="all_component/footer.jsp"%>
</body>
</html>