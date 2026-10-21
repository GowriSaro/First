package webproject.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FetchMedia
 */
@WebServlet("/FetchMedia")
public class FetchMedia extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int rollNo = Integer.parseInt(request.getParameter("rollno"));
		String type = request.getParameter("type"); // "document", "image", "audio", "video"

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/studentSystem", "root",
				"root")) {

			String sql = "SELECT document, image, audio, video FROM studentDetails WHERE rollno=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rollNo);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				InputStream is = null;
				String contentType = "application/octet-stream"; // default

				switch (type) {
				case "document":
					is = rs.getBinaryStream("document");
					contentType = "application/pdf"; // adjust if needed
					break;
				case "image":
					is = rs.getBinaryStream("image");
					contentType = "image/jpeg"; // or image/png
					break;
				case "audio":
					is = rs.getBinaryStream("audio");
					contentType = "audio/mpeg";
					break;
				case "video":
					is = rs.getBinaryStream("video");
					contentType = "video/mp4";
					break;
				}

				if (is != null) {
					response.setContentType(contentType);
					OutputStream os = response.getOutputStream();
					byte[] buffer = new byte[4096];
					int bytesRead;
					while ((bytesRead = is.read(buffer)) != -1) {
						os.write(buffer, 0, bytesRead);
					}
					os.flush();
					os.close();
					is.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
