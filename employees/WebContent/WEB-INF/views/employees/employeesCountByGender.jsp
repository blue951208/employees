<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Count By Gender</title>
</head>
<body>
	<h1>GenderCount</h1>
		<table border="1">
			<thead>
				<tr>
					<th>성별</th>
					<th>인원수</th>
				</tr>
			</thead>	
			<tbody>
				<c:forEach var="employees" items="${list}">
					<tr>
						<td>${employees.gender}</td>
						<td>${employees.cnt}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
</body>
</html>