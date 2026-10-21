<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registered Students</title>
</head>
<body>
<%
List<Map<String,Object>> studentList = (List<Map<String,Object>>)request.getAttribute("studentList");
if(!studentList.isEmpty()){
%>
	<table>
		<thead>
			<tr>
				<th>Rollno</th>
				<th>FirstName</th>
				<th>Email</th>
				<th>Photo</th>
			</tr>
		</thead>
		<tbody>
		<%for(Map<String,Object> studentInfo:studentList) {%>
		<tr>
			<td><%= studentInfo.get("rollNo") %></td>
			<td><%= studentInfo.get("fName") %></td>
			<td><%= studentInfo.get("email") %></td>
			<td><img src="${pageContext.request.contextPath}/uploads/images/<%= studentInfo.get("imageFileName") %>" width="150" /></td>
			<td></td>
		</tr>
		<%}
		
		}%>
		</tbody>

	</table>
</body>
</html>