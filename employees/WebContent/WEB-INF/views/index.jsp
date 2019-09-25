<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
</head>
<body>
	<h1>Index</h1>
		<div>
			<a href="${pageContext.request.contextPath}">HOME</a>
		</div>
		<div>
			employees table total row Count : <%=request.getAttribute("employeesRowCount") %> 
			or ${employeesRowCount}
		</div>
			<table border = "1">
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
		<form method="post" action="${pageContext.request.contextPath}/employees/getEmployeesListBetween">
			<input type="number" name="begin">~<input type="number" name="end">
			<button type="submit">사원 목록</button>
			(${minEmpNo}~${maxEmpNo})
		</form>
</body>
</html>