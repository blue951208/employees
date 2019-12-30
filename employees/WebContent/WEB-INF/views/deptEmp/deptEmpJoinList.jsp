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
<style>

	thead{
		background-color: #58ACFA;
	}
	.custom-select{
		width:200px;
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
			<div class="col-sm-7">
				<!-- 부서를 클릭할 수 있는 select,클릭하면 Servlet으로 데이터 전송 -->
				<form method="get" action="<%=request.getContextPath()%>/deptEmp/getDeptEmpJoinList">
					 <select name="deptName" class="custom-select">
						<c:forEach var="dept" items="${dept}">
							<option value="${dept.deptName}">${dept.deptName}</option>
						</c:forEach>				
						<div></div>
					</select> 
					<button type="submit" class="btn btn-primary">Check</button>
				</form>
					<table border="1" class="table">
						<thead>
							<tr>
								<td>직원 이름</td>
								<td>부서 이름</td>
								<td>from Date</td>
								<td>To Date</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="join" items="${list}">
								<tr>
									<td>${join.name}</td>
									<td>${join.deptName}</td>
									<td>${join.fromDate}</td>
									<td>${join.toDate}</td>
								</tr>
							</c:forEach>	
						</tbody>
					</table>
				<c:if test="${currentPage>1}">
					<a href="${pageContext.request.contextPath}/deptEmp/getDeptEmpJoinList?currentPage=${currentPage-1}">이전</a>
				</c:if>
				<c:if test="${currentPage<lastPage}">
					<a href="${pageContext.request.contextPath}/deptEmp/getDeptEmpJoinList?currentPage=${currentPage+1}">다음</a>
				</c:if>
			</div>
			<div class="col-sm-1"></div>
</div>
		
</body>
</html>