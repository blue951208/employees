<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
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
	<h1>Index</h1>
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
			<form method="post" action="${pageContext.request.contextPath}/employees/getEmployeesListBetween">
				<input type="number" name="begin"> ~ <input type="number" name="end">
					<button type="submit">사원 목록</button>
				(${minEmpNo}~${maxEmpNo})
			</form>
			<br>
			<table class="table" border = "2">
				<tr>
					<td>부서 </td>
					<td>${departmentsRowCount}</td>
				</tr>
				<tr>
					<td>직원 </td>
					<td>${employeesRowCount}</td>
				</tr>
				<tr>
					<td>직책 </td>
					<td>${titlesRowCount}</td>
				</tr>
				<tr>
					<td>연봉</td>
					<td>${salariesRowCount}</td>
				</tr>
				<tr>
					<td>부서-직원</td>
					<td>${deptEmpRowCount}</td>
				</tr>
				<tr>
					<td>매니저</td>
					<td>${deptManagerRowCount}</td>
				</tr>
			</table>
		</div>
		<div class="col-sm-1">
		</div>
	</div>			
			
		<div>
			employees table total row Count : <%=request.getAttribute("employeesRowCount") %> 
			or ${employeesRowCount}
		</div>

</body>
</html>