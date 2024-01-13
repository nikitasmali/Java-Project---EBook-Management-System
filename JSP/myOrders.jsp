<%@page import="com.demo.entity.BookOrder"%>
<%@page import="java.util.List"%>
<%@page import="com.demo.dao.DBUtil"%>
<%@page import="com.demo.dao.BookOrderDaoImpl"%>
<%@page import="com.demo.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="all_component/allCss.jsp"%>
</head>
<body style="background-color: #f7f7f7;">

	<c:if test="${empty userobject }">
		<c:redirect url="login.jsp"></c:redirect>
	</c:if>

	<%@include file="all_component/navbar.jsp"%>

	<div class="container p-1">
		<h3 class="text-center text-primary">Your Orders</h3>
		<table class="table table-striped mt-3">
			<thead class="bg-primary text-white">
				<tr>
					<th scope="col">Order Id</th>
					<th scope="col">Name</th>
					<th scope="col">Book Name</th>
					<th scope="col">Author</th>
					<th scope="col">Price</th>
					<th scope="col">Payment Type</th>
				</tr>
			</thead>
			<tbody>

				<%
				User user = (User) session.getAttribute("userobject");
				BookOrderDaoImpl dao = new BookOrderDaoImpl(DBUtil.getCon());
				List<BookOrder> blist = dao.getBook(user.getEmail());
				for (BookOrder b : blist) {
				%>
				<tr>
					<th scope="row"><%=b.getOrderId()%></th>
					<td><%=b.getUserName()%></td>
					<td><%=b.getBookName()%></td>
					<td><%=b.getAuthor()%></td>
					<td><%=b.getPrice() %> </td>
					<td><%=b.getPaymentType()%></td>
				</tr>
				<%
				}
				%>

			</tbody>
		</table>
	</div>
</body>
</html>