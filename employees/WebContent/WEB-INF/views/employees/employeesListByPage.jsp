<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<a href="${pageContext.request.contextPath}/">HOME</a>
	</div>
		
	<h1>사원 리스트 (10개씩)</h1>

		<table border="1">
			<thead>
				<tr>
					<th>empNo</th>
					<th>firstName</th>
					<th>lastName</th>
					<th>gender</th>
					<th>birthDate</th>
					<th>hireDate</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="employees" items="${list}">
					<tr>
						<td>${employees.empNo}</td>
						<td>${employees.firstName}</td>
						<td>${employees.lastName}</td>
						<td>${employees.gender}</td>
						<td>${employees.birthDate}</td>
						<td>${employees.hireDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	<!-- 페이지가 1보다 크면 다음버튼 -->
	<c:if test="${currentPage>1}">
		<a href ="${pageContext.request.contextPath}/employees/getEmployeesListByPage?currentPage=${currentPage-1}">이전</a>
	</c:if>
	<!-- 페이지가 마지막 페이지보다 작으면 이전버튼 -->
	<c:if test="${currentPage<lastPage}">
		<a href ="${pageContext.request.contextPath}/employees/getEmployeesListByPage?currentPage=${currentPage+1}">다음</a>
	</c:if>
</body>
</html>