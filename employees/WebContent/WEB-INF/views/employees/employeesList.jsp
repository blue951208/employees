<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>사원 목록</h1>
			<form method="get" action="<%=request.getContextPath()%>/employees/GetEmployeesListServlet">
				<select name=limit>
					<option value="10">10</option>
					<option value="20">20</option>
					<option value="30">30</option>
					<option value="40">40</option>
					<option value="50">50</option>
				</select>
				<button type="submit">확인</button>
			</form>
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
				<c:foreach var="employees" items="${list}">
					<tr>
						<td>${employees.empNo}</td>
						<td>${employees.firstName}</td>
						<td>${employees.lastName}</td>
						<td>${employees.birthDate}</td>
						<td>${employees.hireDate}</td>
						<td>${employees.gender}</td>
					</tr>
				</c:foreach>
			</tbody>
		</table>
</body>
</html>