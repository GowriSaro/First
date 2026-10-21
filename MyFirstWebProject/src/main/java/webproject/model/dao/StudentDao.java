package webproject.model.dao;

import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import webproject.model.Student;

public class StudentDao {
	
	private String url;
	private String username;
	private String password;
	
	public StudentDao() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		url="jdbc:mysql://localhost:3307/studentSystem";
		username="root";
		password="root";
		
	}
	
	public Connection getConnection() throws SQLException {
		Connection con =DriverManager.getConnection(url, username, password);
		
		return con;
	}
	
	public boolean registerStudent(Student student) throws SQLException {
		boolean studentRegistered = false;
		Connection con = getConnection();
		
		String insertQuery = "INSERT INTO studentDetails (firstname,lastname,dob,email,gender,"
				+ "skills,contactNo,address,branch)values(?,?,?,?,?,?,?,?,?)";

		PreparedStatement ps = con.prepareStatement(insertQuery);

		ps.setString(1, student.getFirstName());
		ps.setString(2, student.getLastName());
		ps.setDate(3, Date.valueOf(student.getDob()));
		ps.setString(4, student.getEmail());
		ps.setString(5, student.getGender());
		ps.setString(6, student.getSkills().replace("[","").replace("]", ""));
		ps.setLong(7, student.getContactNo());
		ps.setString(8, student.getAddress());
		ps.setString(9, student.getBranch());
		
		int result = ps.executeUpdate();
		
		if(result>0) {
			System.out.println("Student Registered succesfully");
			studentRegistered = true;
		}
		
		ps.close();
		con.close();
		
		return studentRegistered;
		
	}
	
	
	public List<Student> fetchAllStudents() throws SQLException {
		Connection con = getConnection();
		String retrievalQuery = "SELECT * FROM studentdetails";

		Statement stmt = con.createStatement();

		ResultSet resultSet = stmt.executeQuery(retrievalQuery);
		
		List<Student> studentList = new ArrayList<Student>();
		
		while(resultSet.next()) {
			Student student = new Student();
			student.setRollNo(resultSet.getInt(1));
			student.setFirstName(resultSet.getString("firstname"));
			student.setLastName(resultSet.getString(3));
			student.setDob(resultSet.getDate(4).toString());
			student.setEmail(resultSet.getString(5));
			student.setGender(resultSet.getString(6));
			student.setSkills(resultSet.getString(7));
			student.setContactNo(resultSet.getLong(8));
			student.setAddress(resultSet.getString(9));
			student.setBranch(resultSet.getString(10));
			student.setDocument(resultSet.getBinaryStream(11));
			student.setImage(resultSet.getBinaryStream(12));
			student.setAudio(resultSet.getBinaryStream(13));
			student.setVideo(resultSet.getBinaryStream(14));
			student.setDocType(resultSet.getString(15));
			studentList.add(student);
			
		}
		
		return studentList;
		
		
	}

}
