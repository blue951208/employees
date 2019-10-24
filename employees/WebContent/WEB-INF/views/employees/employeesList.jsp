<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<h1>사원 목록</h1>
			<div>
				<a href="${pageContext.request.contextPath}">HOME</a>
			</div><!--  메인 페이지로 이동 -->
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
				</ul><!-- 메뉴  -->
			</div>
		<div class="col-sm-7 container">
			<form method="get" action="<%=request.getContextPath()%>/employees/GetEmployeesListServlet">
				<select name="limit">
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
		</div>
		<div class="col-sm-1"></div>
</body>
</html>