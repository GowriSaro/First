package webproject.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webproject.model.Student;
import webproject.model.dao.StudentDao;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet(urlPatterns ="/RegistrationServlet",name="RegistrationServlet")
@MultipartConfig
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Inside init method() of Registration Servlet");
		
		  String email = config.getInitParameter("email"); 
		  System.out.println(email);
		  String user = config.getInitParameter("user"); 
		  System.out.println(user);
		  
		  ServletContext context = config.getServletContext();
		  System.out.println(context.getInitParameter("username"));
		  System.out.println(context.getInitParameter("password"));
		  
		  
		  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName= request.getParameter("fName");
		String lastName = request.getParameter("lName");
		String dob = request.getParameter("dob");
		//Date dob = Date.valueOf(request.getParameter("dob"));
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		//String contactNo = request.getParameter("contactNo");
		long contactNo = Long.parseLong(request.getParameter("contactNo"));
		String[] skills = request.getParameterValues("skills");

		String address = request.getParameter("address");
		String branch = request.getParameter("branch");
		
		Student student = new Student(firstName,lastName,dob,email,gender,
							Arrays.toString(skills),contactNo,address,branch);
		
		
		try {
			StudentDao studentDao = new StudentDao();
			
			boolean studentRegistered = studentDao.registerStudent(student);
			
			if(studentRegistered) {
				response.sendRedirect("RegistrationSuccess.jsp?fname="+firstName);
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
