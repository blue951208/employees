<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OrderBy</title>
</head>
<body>
	<h1>OrderBy</h1>
		<div>
			<a href="${pageContext.request.contextPath}">HOME</a>
		</div>
	<table border="1">
		<thead>
			<tr>
				<th>empNo</th>
				<th>firstName</th>
				<th>lastName</th>
				<th>birthDate</th>
				<th>hireDate</th>
				<th>gender</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="employees" items="${list}">
				<tr>
					<td>${employees.empNo}</td>
					<td>${employees.firstName}</td>
					<td>${employees.lastName}</td>
					<td>${employees.birthDate}</td>
					<td>${employees.hireDate}</td>
					<td>${employees.gender}</td>
				</tr>
			</c:forEach>	
		</tbody>
	</table>
</body>
</html>