package webproject.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet(urlPatterns = "/TestServlet", name = "TestServlet")
@MultipartConfig
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Inside init method()");
//Above class rewrite the annotation as :
		//@WebServlet(urlPatterns = "/TestServlet", name = "TestServlet")
		  String email = config.getInitParameter("email"); 
		  System.out.println(email);
		  String user = config.getInitParameter("user"); 
		  System.out.println(user);
		  
		  ServletContext context = config.getServletContext();
		  System.out.println(context.getInitParameter("username"));
		  System.out.println(context.getInitParameter("password"));
		  
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Inside service method()");
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

		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(dob);
		System.out.println(gender);
		System.out.println(email);
		System.out.println(contactNo);
		System.out.println(skill);
		System.out.println(Arrays.toString(skills));

		System.out.println(address);
		System.out.println(branch);

		System.out.println(docName.getSubmittedFileName());
		System.out.println(docName.getContentType());

		System.out.println(imageName.getSubmittedFileName());
		System.out.println(imageName.getContentType());

		System.out.println(audioFileName.getSubmittedFileName());
		System.out.println(audioFileName.getContentType());

		System.out.println(videoFileName.getSubmittedFileName());
		System.out.println(videoFileName.getContentType());

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3307/studentSystem";
			String userName = "root";
			String password = "root";
			Connection conn = DriverManager.getConnection(url, userName, password);

			String insertQuery = "INSERT INTO studentDetails (firstname,lastname,dob,email,gender,"
					+ "skills,contactNo,address,branch,document,image,audio,video)values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement ps = conn.prepareStatement(insertQuery);

			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setDate(3, Date.valueOf(dob));
			ps.setString(4, email);
			ps.setString(5, gender);
			ps.setString(6, Arrays.toString(skills).replace("[","").replace("]", ""));
			ps.setLong(7, Long.parseLong(contactNo));
			ps.setString(8, address);
			ps.setString(9, branch);
			ps.setBinaryStream(10, docName.getInputStream());
			ps.setBinaryStream(11, imageName.getInputStream());
			ps.setBinaryStream(12, audioFileName.getInputStream());
			ps.setBinaryStream(13, videoFileName.getInputStream());

			System.out.println(ps);
			int result = ps.executeUpdate();

			if (result > 0) {
				System.out.println("Student Registered successfully");
				String retrievalQuery = "SELECT * FROM studentdetails";
				
				Statement stmt = conn.createStatement();
				
				ResultSet resultSet = stmt.executeQuery(retrievalQuery);
				
				while(resultSet.next()) {
					request.setAttribute("rollno", resultSet.getString(1));
					request.setAttribute("fName", resultSet.getString(2));
					request.setAttribute("image", resultSet.getBinaryStream(12));
				}
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Forward: getting forward to next page
		request.getRequestDispatcher("successJsp.jsp").forward(request, response);

		// response.getWriter().append("Registration done for : "+firstName);

		// PrintWriter out = response.getWriter();

		/*
		 * response.setContentType("text/html");
		 * response.getWriter().println("<!DOCTYPE html>"); response.getWriter().
		 * println("<html><head><title>Registration Sucess</title></head>");
		 * response.getWriter().println("<body>Registration done for "+firstName+
		 * "</body></html>");
		 */

		// response.sendRedirect("success.html");

		// Sharing request parameter through URL Rewriting
		// Redirect :getting redirected to next page
		// response.sendRedirect("successJsp.jsp?fName="+firstName+"&lName="+lastName);

		


	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Inside doGet method()");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("Inside doPost method()");
	}

}
