package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
@MultipartConfig
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String firstName = request.getParameter("fName");
		String lastName = request.getParameter("lName");
		String dob = request.getParameter("dob");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String contactNo = request.getParameter("contactNo");
		String skill = request.getParameter("skills");
		String[] skills = request.getParameterValues("skills");

		String address = request.getParameter("address");
		String branch = request.getParameter("branch");

		Part docName = request.getPart("docName");
		Part imageName = request.getPart("imageName");
		Part audioFileName = request.getPart("audioFileName");
		Part videoFileName = request.getPart("videoFileName");

		String uploadDir = getServletContext().getRealPath("/") + "uploads";
		File uploadFolder = new File(uploadDir+"/images");
		if (!uploadFolder.exists())
			uploadFolder.mkdirs();

		String imageFileName = Paths.get(imageName.getSubmittedFileName()).getFileName().toString();
		File file = new File(uploadFolder, imageFileName);

		// Save file locally

		InputStream input = imageName.getInputStream();
		Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

		// Save path in DB
		String imagePath = "uploads/images/" + imageFileName;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3307/studentSystem";
			String userName = "root";
			String password = "root";
			Connection conn = DriverManager.getConnection(url, userName, password);

			String insertQuery = "INSERT INTO studentDetails1(firstname,lastname,dob,email,gender,"
					+ "skills,contactNo,address,branch,document,image,audio,video)values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement ps = conn.prepareStatement(insertQuery);

			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setDate(3, Date.valueOf(dob));
			ps.setString(4, email);
			ps.setString(5, gender);
			ps.setString(6, Arrays.toString(skills).replace("[", "").replace("]", ""));
			ps.setLong(7, Long.parseLong(contactNo));
			ps.setString(8, address);
			ps.setString(9, branch);
			ps.setString(10, docName.getSubmittedFileName());
			ps.setString(11, imageFileName);
			ps.setString(12, audioFileName.getSubmittedFileName());
			ps.setString(13, videoFileName.getSubmittedFileName());

			System.out.println(ps);
			int result = ps.executeUpdate();

			if (result > 0) {
				System.out.println("Student Registered successfully");
			}
			
			response.sendRedirect("successJsp.jsp");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Forward: getting forward to next page
				request.getRequestDispatcher("viewStudentDetails.jsp").forward(request, response);


	}

}
