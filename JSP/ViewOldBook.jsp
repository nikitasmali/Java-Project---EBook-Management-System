<%@page import="com.demo.dao.DBUtil"%>
<%@page import="com.demo.entity.BookDetails"%>
<%@page import="java.util.List"%>
<%@page import="com.demo.dao.BooksDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All New Book</title>
<%@include file="all_component/allCss.jsp"%>
</head>
<body>
<%@include file="all_component/navbar.jsp"%>
<div class="container-fluid">
		<div class="row p-3">
		<%
			BooksDaoImpl dao = new BooksDaoImpl(DBUtil.getCon());
			List<BookDetails> list = dao.getAllOldBook();
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
							Categories :
							<%=b.getBookCategory()%></p>
						<div class="row ml-5">
							 <a href="viewBooks.jsp?bookId=<%=b.getBookId() %>" class="btn btn-success btn-sm ml-5">Details</a>
							  <a href="" class="btn btn-danger btn-sm ml-1"><%=b.getPrice()%></a>
						</div>
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