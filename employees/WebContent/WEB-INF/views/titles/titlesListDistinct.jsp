<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Titles List(Distinct)</title>
</head>
<body>
	<h1>업무 목록(중복 제거)</h1>
		<ol>
			<c:forEach var="row" items="${list}">	
				<li>${row.title}</li>
			</c:forEach>
		</ol>
</body>
</html>