<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Salaries 통계</title>
</head>
<body>
	<h1>연봉 통계</h1>
		<table border="1">
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
</body>
</html>