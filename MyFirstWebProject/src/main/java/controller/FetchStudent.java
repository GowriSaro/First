package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class FetchStudent
 */
@WebServlet("/FetchStudent")
public class FetchStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3307/studentSystem";
			String userName = "root";
			String password = "root";
			Connection conn = DriverManager.getConnection(url, userName, password);
			
			String retrievalQuery = "SELECT * FROM studentdetails1;";

			Statement stmt = conn.createStatement();

			ResultSet resultSet = stmt.executeQuery(retrievalQuery);
			List<Map<String,Object>> studentList = new ArrayList<Map<String,Object>>();
			
			while (resultSet.next()) {
				Map<String,Object> studentInfo = new HashMap<String,Object>();
				studentInfo.put("rollNo", resultSet.getString(1));
				studentInfo.put("fName", resultSet.getString(2));
				studentInfo.put("email", resultSet.getString("email"));
				studentInfo.put("imageFileName", resultSet.getString(12));
				studentList.add(studentInfo);
			}
			
			request.setAttribute("studentList",studentList);
		
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
