<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Salaries 통계</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</head>
<style>
	thead{
		background-color: #58ACFA;
	}
</style>
<body>
	<h1><a href="${pageContext.request.contextPath}">HOME</a></h1>
			<!--  메인 페이지로 이동 -->
			<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
	<div class="row">
			<div class="col-sm-4">
				<ul>
					<li>
						<a href="${pageContext.request.contextPath}/departments/getDepartmentsList">부서 목록</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/employees/GetEmployeesListServlet">사원 목록</a>
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
					<li>
						<a href="${pageContext.request.contextPath}/deptEmp/getDeptEmpJoinList">부서별 인원</a>
					</li>
				</ul>
			</div>
		<div class="col-sm-7 container">
			<table border="1" class="table">
				<thead>
					<tr>
						<td>count</td>
						<td>sum</td>
						<td>avg</td>
						<td>max</td>
						<td>min</td>
						<td>std</td>
					</tr>
				</thead>	
				<tbody>
					<tr>
						<td>${map.count}</td>
						<td>${map.sum}</td>
						<td>${map.avg}</td>
						<td>${map.max}</td>
						<td>${map.min}</td>
						<td>${map.std}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="col-sm-1"></div>
</body>
</html>