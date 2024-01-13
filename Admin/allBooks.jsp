<%@page import="com.demo.entity.BookDetails"%>
<%@page import="java.util.List"%>
<%@page import="com.demo.dao.DBUtil"%>
<%@page import="com.demo.dao.BooksDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Books</title>
<%@include file="allCss.jsp"%>
</head>
<body>
	<%@include file="navbar.jsp"%>
	
	<c:if test="${empty userobject }">
	<c:redirect url="../login.jsp" />
	</c:if>
	
	<h3 class="text-center">Welcome Admin</h3>

	<c:if test="${not empty successmsg }">
		<h5 class="text-center text-success">${successmsg }</h5>>
	<c:remove var="successmsg" scope="session" />
	</c:if>

	<c:if test="${not empty failedmsg }">
		<h5 class="text-center text-danger">${failedmsg }</h5>
		<c:remove var="failedmsg" scope="session" />
	</c:if>

	<table class="table table-striped">
		<thead class="bg-primary text-white">
			<tr>
				<th scope="col">Book Id</th>
				<th scope="col">Book Name</th>
				<th scope="col">Author</th>
				<th scope="col">Price</th>
				<th scope="col">Categories</th>
				<th scope="col">Status</th>
				<th scope="col">Action</th>
			</tr>
		</thead>
		<tbody>

			<%
			BooksDaoImpl impl = new BooksDaoImpl(DBUtil.getCon());
			List<BookDetails> list = impl.getAllBooks();
			for (BookDetails b : list) {
			%>
			<tr>
				<td><%=b.getBookId()%></td> 
				<td><%=b.getBookName() %> </td>
				<td><%=b.getAuthor()%></td>
				<td><%=b.getPrice()%></td>
				<td><%=b.getBookCategory()%></td>
				<td><%=b.getStatus()%></td>
				<td><a href="editBooks.jsp?id=<%=b.getBookId()%>"
					class="btn btn-sm btn-primary"><i
						class="fa-solid fa-pen-to-square"></i> Edit</a> <a
					href="../DeleteBookServlet?id=<%=b.getBookId()%>"
					class="btn btn-sm btn-danger"><i class="fa-solid fa-trash-can"></i>
						Delete</a></td>
			</tr>
			<%
			}
			%>

		</tbody>
	</table>
	<div style="margin-top: 270px">
		<%@include file="footer.jsp"%>
	</div>
</body>
</html>