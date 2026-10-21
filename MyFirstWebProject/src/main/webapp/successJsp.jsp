<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RegistrationSuccess</title>
</head>
<body>
	<h1>This is a JSP File redirected from Servlet</h1>
	<h3>From Request Parameter</h3>
	<%  String firstName = request.getParameter("fName"); 
	String lastName = request.getParameter("lName");
	String email = request.getParameter("email");
		%>

	<b>First Name : </b><%= firstName %><br>
	<b>Last Name :</b><%= lastName %><br>
	<b>Email :</b><%= email %><br>
	<br><br>
	<h3>From Request Attribute</h3>
	<%
	String fName = (String)request.getAttribute("fName"); 
	int rollNo = Integer.parseInt((String)request.getAttribute("rollno"));
	String emailid = (String)request.getAttribute("email");
	String imageFileName = (String)request.getAttribute("imageFileName");
	out.println(fName);%><br>
	<%out.println(rollNo);%><br>
	<%out.println(emailid);%><br>
	<img src="FetchMedia?rollNo=<%=rollNo%>&type=image" alt="Alternate image" width="200px" />

	

</body>
</html>