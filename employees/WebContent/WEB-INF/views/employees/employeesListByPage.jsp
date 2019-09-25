<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</head>
<body>
	<div>
				<a href="${pageContext.request.contextPath}">HOME</a>
			</div>
		<div class="row">
			<div class="col-sm-4">
				<ul>
					<li>
						<a href="${pageContext.request.contextPath}/departments/getDepartmentsList">부서 목록</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/employees/getEmployeesListServlet">사원 목록</a>
					</li>
			
					<li>
						<a href="${pageContext.request.contextPath}/employees/getEmployeesListOrderBy?order=asc">오름차순(limit50)</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/employees/getEmployeesListOrderBy?order=desc">내림차순(limit50)</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/titles/getTitlesListDistinct">업무 목록(중복없음)</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/salaries/getSalariesStatistics">연봉통계</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/employees/getEmployeesCountByGender">직원 수(성별)</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/departments/getDepartmentCountByDeptNo">직원 수(부서별)</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/employees/getEmployeesListByPage">사원정보 (10개씩 페이징)</a>
					</li>
				</ul>
			</div>
		<div class="col-sm-7 container">
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
	
	<c:forEach var="i" begin="1" end="10">
		<a href="${pageContext.request.contextPath}/employees/getEmployeesListByPage?currentPage=${i}"><c:out value="${i}">${i}</c:out></a>
	</c:forEach>
	
	<!-- 페이지가 마지막 페이지보다 작으면 이전버튼 -->
	<c:if test="${currentPage<lastPage}">
		<a href ="${pageContext.request.contextPath}/employees/getEmployeesListByPage?currentPage=${currentPage+1}">다음</a>
	</c:if>
		</div>
		<div class="col-sm-1">
		</div>
</body>
</html>