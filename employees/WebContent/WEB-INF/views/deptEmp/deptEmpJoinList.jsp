<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<td>직원 이름</td>
				<td>부서 이름</td>
				<td>from Date</td>
				<td>To Date</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="join" items="${list}">
				<tr>
					<td>${join.name}</td>
					<td>${join.deptName}</td>
					<td>${join.fromDate}</td>
					<td>${join.toDate}</td>
				</tr>
			</c:forEach>	
		</tbody>
	</table>
</body>
</html>