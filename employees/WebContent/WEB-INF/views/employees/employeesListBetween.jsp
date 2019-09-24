<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Between List</title>
</head>
<body>
	<h1>Between</h1>
		<table border="1">
			<thead>
				<tr>
					<td>사원 번호></td>
					<td>사원 이름></td>
					<td>사원 성></td>
					<td>사원 생일></td>
					<td>입사 날짜></td>
					<td>사원 성별></td>
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