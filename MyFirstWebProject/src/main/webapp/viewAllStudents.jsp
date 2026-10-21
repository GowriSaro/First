<%@page import="java.util.Base64"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="webproject.model.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ViewAllStudents</title>
</head>
<body>
<%
List<Student> studentList =(List<Student>)request.getAttribute("studentList");

if(!studentList.isEmpty()){
%>

<table border="1">
	<thead>
		<tr>
			<th>Roll No</th>
			<th>First Name</th>
			<th>Dob</th>
			<th>Image</th>
			<th>Document</th>
			<th>Audio</th>
			<th>Video</th>
	</thead>
	
	<tbody>
	<%for(Student student:studentList){ %>
		<tr>
			<td><%=student.getRollNo() %></td>
			<td><%=student.getFirstName() %></td>
			<td><%=student.getDob()%></td>
			
			
			<!--    <td><img src="FetchMedia?rollno=<%= student.getRollNo() %>&type=image" width="100"/></td>
			  <td>
			    <audio controls>
			      <source src="FetchMedia?rollno=<%= student.getRollNo() %>&type=audio" type="audio/mpeg">
			    </audio>
			  </td>
			  <td>
			    <video width="200" controls>
			      <source src="FetchMedia?rollno=<%= student.getRollNo() %>&type=video" type="video/mp4">
			    </video>
			  </td>
			  <td><a href="FetchMedia?rollno=<%= student.getRollNo() %>&type=document">Download</a></td>
			-->
			
		  	<%
			byte[] imageBytes = null;
			if (student.getImage() != null) {
		        InputStream is = (InputStream) student.getImage();
		        ByteArrayOutputStream os = new ByteArrayOutputStream();
		        byte[] buffer = new byte[4096];
		        int bytesRead;
		        while ((bytesRead = is.read(buffer)) != -1) {
		            os.write(buffer, 0, bytesRead);
		        }
		        imageBytes = os.toByteArray();
		    }
		    String base64Image = imageBytes != null ? Base64.getEncoder().encodeToString(imageBytes) : "";
			%>
			<td><img  src="data:image/jpeg;base64,<%= base64Image %>" alt="Image not found" width="100px" height="100px" ></td>
			<%
			    byte[] docBytes = null;
			    if (student.getDocument() != null) {
			        InputStream is = (InputStream) student.getDocument();
			        ByteArrayOutputStream os = new ByteArrayOutputStream();
			        byte[] buffer = new byte[4096];
			        int bytesRead;
			        while ((bytesRead = is.read(buffer)) != -1) {
			            os.write(buffer, 0, bytesRead);
			        }
			        docBytes = os.toByteArray();
			    }
			    String base64Doc = docBytes != null ? Base64.getEncoder().encodeToString(docBytes) : "";
			%>

				<td><a href="data:<%=student.getDocType() %>;base64,<%= base64Doc %>" download="studentDoc">
    			Download Document
				</a></td>			<%
		    byte[] audioBytes = null;
		    if (student.getAudio() != null) {
		        InputStream is = (InputStream) student.getAudio();
		        ByteArrayOutputStream os = new ByteArrayOutputStream();
		        byte[] buffer = new byte[4096];
		        int bytesRead;
		        while ((bytesRead = is.read(buffer)) != -1) {
		            os.write(buffer, 0, bytesRead);
		        }
		        audioBytes = os.toByteArray();
		    }
		    String base64Audio = audioBytes != null ? Base64.getEncoder().encodeToString(audioBytes) : "";
		%>

		<td><audio controls>
		    <source src="data:audio/mpeg;base64,<%= base64Audio %>" type="audio/mpeg">
		    Your browser does not support the audio element.
		</audio></td>
		
				<%
		    byte[] videoBytes = null;
		    if (student.getVideo() != null) {
		        InputStream is = (InputStream) student.getVideo();
		        ByteArrayOutputStream os = new ByteArrayOutputStream();
		        byte[] buffer = new byte[4096];
		        int bytesRead;
		        while ((bytesRead = is.read(buffer)) != -1) {
		            os.write(buffer, 0, bytesRead);
		        }
		        videoBytes = os.toByteArray();
		    }
		    String base64Video = videoBytes != null ? Base64.getEncoder().encodeToString(videoBytes) : "";
		%>

			<td><video width="320" height="240" controls>
			    <source src="data:video/mp4;base64,<%= base64Video %>" type="video/mp4">
			    Your browser does not support the video tag.
			</video></td>
		</tr>
	<%}%>
	</tbody>
</table>

<% } %>
</body>
</html>