<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
</head>
<body>
	<h1>Index</h1>
		<ul>
			<li><a href="${pageContext.request.contextPath}/departments/getDepartmentsList">부서 목록</a></li>
			<li><a href="${pageContext.request.contextPath}/employees/GetEmployeesListServlet">사원 목록</a></li>
		</ul>
		<div>
			employees table total row Count : <%=request.getAttribute("employeesRowCount") %>
		</div>
</body>
</html>