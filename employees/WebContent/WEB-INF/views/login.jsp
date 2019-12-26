<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1>로그인 </h1>
			<form method="post" action="${pageContext.request.contextPath}/login">
				
					<label>firstName:</label>
				<div>
					<input type="text" name="firstName" value='s'>
				</div>
				
					<label>lastName:</label>
				<div>
					<input type="text" name="lastName" value='s'>
				</div>
				
					<label>empNo:</label>
				<div>
					<input type="number" name="empNo" value=1>
				</div>
				<div>
					<button type="submit">Login</button>
				</div>
			</form>
	</div>
</body>
</html>