package webproject.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webproject.model.Student;
import webproject.model.dao.StudentDao;

/**
 * Servlet implementation class FetchAllStudents
 */
@WebServlet("/FetchAllStudents")
public class FetchAllStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchAllStudents() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			StudentDao studentDao = new StudentDao();
			
			List<Student> studentList = studentDao.fetchAllStudents();
		
			if(!studentList.isEmpty()) {
				request.setAttribute("studentList", studentList);
				
				request.getRequestDispatcher("viewAllStudents.jsp").forward(request, response);
				//response.sendRedirect("viewAllStudents.jsp?studentList="+studentList);
			}else {
				
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
