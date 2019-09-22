<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List</title>
</head>
<body>
	<h1>부서목록</h1>
		<table border="1">
			<thead>
				<tr>
					<td>부서 번호</td>
					<td>부서 이름</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="departments" items="${list}">
					<tr>
						<td>${departments.deptNo}</td>
						<td>${departments.deptName}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
</body>
</html>