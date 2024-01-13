<%@page import="com.demo.entity.BookOrder"%>
<%@page import="java.util.List"%>
<%@page import="com.demo.dao.DBUtil"%>
<%@page import="com.demo.dao.BookOrderDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Orders</title>
<%@include file="allCss.jsp"%>
</head>
<body>
<c:if test="${empty userobject }">
	<c:redirect url="../login.jsp" />
	</c:if>
<%@include file="navbar.jsp"%>
<h3 class="text-center">Welcome Admin</h3>
<table class="table table-striped">
  <thead class="bg-primary text-white">
    <tr>
      <th scope="col">Order Id</th>
      <th scope="col">Name</th>
      <th scope="col">Email</th>
      <th scope="col">Address</th>
       <th scope="col">Phone No</th>
        <th scope="col">Book Name</th>
         <th scope="col">Author</th>
         <th scope="col">Price</th>
         <th scope="col">Payment Type</th>
    </tr>
  </thead>
  <tbody>
  
  <%
  	BookOrderDaoImpl dao = new BookOrderDaoImpl(DBUtil.getCon());
    List<BookOrder> blist = dao.getAllBookOrders();
    for(BookOrder b:blist)
    {%>
    	<tr>
      <th scope="row"><%=b.getOrderId() %></th>
      <td><%=b.getUserName() %></td>
      <td><%=b.getEmail() %></td>
      <td><%=b.getFullAdd() %></td>
      <td><%=b.getPhno() %></td>
      <td><%=b.getBookName() %></td>
      <td><%=b.getAuthor() %></td>
      <td><%=b.getPrice() %></td>
      <td><%=b.getPaymentType() %></td>
    </tr>
    <%	
    }
  %>
  
  </tbody>
</table>
<div style="margin-top: 180px">
<%@include file="footer.jsp" %>
</div>
</body>
</html>