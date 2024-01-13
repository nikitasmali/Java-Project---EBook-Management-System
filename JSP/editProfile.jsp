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
<%@include file="all_component/navbar.jsp"%>

<div class="container">
		<div class="row mt-4">
			<div class="col-md-4 offset-md-4">
				<div class="card">
					<div class="card-body">
						<h3 class="text-center text-primary p-1">Edit Profile</h3>
						
						
					<c:if test="${not empty failedmsg }">
					<h5 class="text-center text-danger">${failedmsg }</h5>
					<c:remove var="failedmsg" scope="session"/>
					</c:if>
					
					<c:if test="${not empty successmsg }">
					<h5 class="text-center text-success">${successmsg }</h5>
					<c:remove var="successmsg" scope="session"/>
					</c:if>
						
						<form action="update_profile" method="post">
						<input type="hidden" value="${userobject.id }" name="id">
							<div class="form-group">
								<label for="exampleInputEmail1">Name </label> <input
									type="text" class="form-control" id="exampleInputEmail1"
									aria-describedby="emailHelp" placeholder="Enter Name"
									required="required" name="fname" value="${userobject.name }">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">Email address </label> <input
									type="email" class="form-control" id="exampleInputEmail1"
									aria-describedby="emailHelp" placeholder="Enter email"
									required="required" name="email" value="${userobject.email }">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">Phone Number </label> <input
									type="number" class="form-control" id="exampleInputEmail1"
									aria-describedby="emailHelp" placeholder="Enter Phone No"
									required="required" name="phno" value="${userobject.phno }">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">Password </label> <input
									type="password" class="form-control" id="exampleInputPassword1"
									placeholder="Password" required="required" name="password">
							</div>
							<div class="text-center p-2">
							<button type="submit" class="btn btn-primary">Update</button>
						</div>
						</form>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>