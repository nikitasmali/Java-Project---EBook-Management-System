<%@page import="com.demo.entity.BookDetails"%>
<%@page import="com.demo.dao.DBUtil"%>
<%@page import="com.demo.dao.BooksDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="allCss.jsp" %>
</head>
<body style="background-color: #f0f2f2">
<%@include file="navbar.jsp"%>
<div class="container">
<div class="row">
<div class="col-md-4 offset-md-4">
<div class="card">
<div class="card-body">
	<h4 class="text-center">Edit Books</h4>
	
	<%
	 int id = Integer.parseInt(request.getParameter("id"));
	BooksDaoImpl dao = new BooksDaoImpl(DBUtil.getCon());
	BookDetails  bookDetails = dao.getBookById(id);
	%>
	<form action="../editbooks" method="post">
	
		<input type="hidden" name="id" value="<%=bookDetails.getBookId()%>"> 
		<div class="form-group">
		<label for="exampleInputEmail1">Book Name</label>
		<input name="bname" type="text" class="form-control" 
				id="exampleInputEmail1" aria-describedy="emailHelp" value="<%=bookDetails.getBookName()%>">
		</div>
		
		<div class="form-group">
		<label for="exampleInputEmail">Author Name</label>
		<input name="author" type="text" class="form-control" 
				id="exampleInputEmail1" aria-describedy="emailHelp" value="<%=bookDetails.getAuthor()%>">
		</div>
		
		<div class="form-group">
		<label for="exampleInputPassword1">Price</label>
		<input name="price" type="number" class="form-control" 
				id="exampleInputPassword1" value="<%=bookDetails.getPrice()%>">
		</div>
		
		<div class="form-group">
		<label for="inputState">Book Status</label>
		<select id="inputState" name="status" class="form-control">
		<% if("Active".equals(bookDetails.getStatus()))
			{%>
				<option value="Active">Active</option>	
				<option value="Inactive">Inactive</option>
		<% }
		else
		{%>
				<option value="Inactive">Inactive</option>
				<option value="Active">Active</option>	
		<%} %>
		
		
		</select>
		</div>
	
		<button type="submit" class="btn btn-primary">Update</button>
	</form>
</div>
</div>
</div>
</div>
</div>
<div style="margin-top: 180px">
<%@include file="footer.jsp" %>
</div>
</body>
</html>