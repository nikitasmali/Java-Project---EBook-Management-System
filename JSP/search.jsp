<%@page import="com.demo.entity.User"%>
<%@page import="com.demo.entity.BookDetails"%>
<%@page import="java.util.List"%>
<%@page import="com.demo.dao.DBUtil"%>
<%@page import="com.demo.dao.BooksDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Recent Books</title>
<%@include file="all_component/allCss.jsp"%>
<style type="text/css">
.crd-ho:hover {
	background-color: #f2e4e4;
}
</style>
</head>
<body>
<%
		User user=(User)session.getAttribute("userobject"); 
	%>

	<%@include file="all_component/navbar.jsp"%>
	<div class="container-fluid">
		<div class="row p-3">
				<%
				String ch = request.getParameter("ch");
				BooksDaoImpl dao2 = new BooksDaoImpl(DBUtil.getCon());
				List<BookDetails> list = dao2.getBookBySearch(ch);
				for (BookDetails b : list) {
				%>
				<div class="col-md-3">
					<div class="card crd-ho mt-2">
						<div class="card-body text-center">
							<img alt="" src="book/<%=b.getPhoto()%>"
								style="width: 150px; height: 200px;" class="img-thumblin">
							<p><%=b.getBookName()%></p>
							<p><%=b.getAuthor()%></p>
							<p>

								<%
								if (b.getBookCategory().equals("Old")) {
								%>
								Categories :<%=b.getBookCategory()%></p>
							<div class="row ml-5">
								<a href="" class="btn btn-success btn-sm ml-5">Details</a> <a
									href="" class="btn btn-danger btn-sm ml-1"><%=b.getPrice()%></a>
							</div>
							<%
							} else {
							%>
							Categories :<%=b.getBookCategory()%></p>
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
	</div>
</body>
</html>