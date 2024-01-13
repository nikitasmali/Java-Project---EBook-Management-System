<%@page import="com.demo.entity.BookDetails"%>
<%@page import="java.util.List"%>
<%@page import="com.demo.entity.User"%>
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
<%@include file="all_component/allCss.jsp"%>
</head>
<body style="background-color: #f7f7f7;">
<%@include file="all_component/navbar.jsp"%>

						<c:if test="${not empty sucMsg }">
						<div class="alert alert-success text-center">${sucMsg }</div>
						<c:remove var="sucMsg"/>
						</c:if>
						
<div class="container p-5">
<table class="table table-striped">
  <thead class="bg-primary text-white"> 
    <tr>
      <th scope="col">Book Name</th>
      <th scope="col">Author</th>
      <th scope="col">Price</th>
      <th scope="col">Category</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  <tbody>
  
  <%
      User u = (User)session.getAttribute("userobject");
      String email = u.getEmail();
	  BooksDaoImpl dao = new BooksDaoImpl(DBUtil.getCon());
 	List<BookDetails> list = dao.getBookByOld(email, "Old");
 	for(BookDetails b:list)
 	{%>
 		<tr>
      <td><%=b.getBookName() %></td>
      <td><%=b.getAuthor() %></td>
      <td><%=b.getPrice() %></td>
       <td><%=b.getBookCategory() %></td>
      <td><a href="delete_old_book?email=<%=email%>&&bookId=<%=b.getBookId() %>" class="btn btn-sm btn-danger">Delete</a></td>
    </tr>
 	
 	<%	
 	}
  %>
  
  </tbody>
</table>
</div>
</body>
</html>