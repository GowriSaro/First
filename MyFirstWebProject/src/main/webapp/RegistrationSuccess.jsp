<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RegistrationSuccess</title>
</head>
<body>
	<%
		String firstName = request.getParameter("fname");
	%>
	Registration done successfully for <%= firstName %>
</body>
</html>