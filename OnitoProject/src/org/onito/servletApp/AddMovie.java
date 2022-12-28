package org.onito.servletApp;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class AddMovie extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws 
	ServletException, IOException{
		String uTconst=req.getParameter("tc");
		String uTitleType=req.getParameter("tt");
		String uPrimaryTitle=req.getParameter("pt");
		String uRuntimeMinutes=req.getParameter("rtm");
		int uRuntime=Integer.parseInt(uRuntimeMinutes);
		String uGenres=req.getParameter("gn");
		
		Connection con=null;
		PreparedStatement pstmt=null;
		String usql="insert into onito.movies values(?,?,?,?,?)";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=dinga");
			pstmt=con.prepareStatement(usql);
			pstmt.setString(1, uTconst);
			pstmt.setString(2, uTitleType);
			pstmt.setString(3, uPrimaryTitle);
			pstmt.setInt(4, uRuntime);
			pstmt.setString(5, uGenres);
			pstmt.executeUpdate();
			
			PrintWriter out=resp.getWriter();
			out.println("<!DOCTYPE html>\r\n" + 
					"<html>\r\n" + 
					"\r\n" + 
					"<head>\r\n" + 
					"    <title>Register Page</title>\r\n" + 
					"    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4\" crossorigin=\"anonymous\"></script>\r\n" + 
					"    <meta charset=\"utf-8\">\r\n" + 
					"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
					"    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\r\n" + 
					"</head>\r\n" + 
					"<style>\r\n" + 
					"    body {\r\n" + 
					"        background-image: url(\"movie.jpg\");\r\n" + 
					"        background-repeat: no-repeat;\r\n" + 
					"        background-attachment: fixed;\r\n" + 
					"        background-size: 100% 100%;\r\n" + 
					"    }\r\n" + 
					"\r\n" + 
					"    .btn-primary {\r\n" + 
					"        background-color: blue;\r\n" + 
					"        width: 372px;\r\n" + 
					"        font-size: 16px;\r\n" + 
					"    }\r\n" + 
					"\r\n" + 
					"    .footer {\r\n" + 
					"        position: fixed;\r\n" + 
					"        bottom: 0px;\r\n" + 
					"        height: 20px;\r\n" + 
					"        width: 100%;\r\n" + 
					"        background-color: grey;\r\n" + 
					"    }\r\n" + 
					"\r\n" + 
					"    div.main {\r\n" + 
					"        border: solid 1px grey;\r\n" + 
					"        border-radius: 5px;\r\n" + 
					"        margin: 70px;\r\n" + 
					"        height: 153px;\r\n" + 
					"        width: 1035px;\r\n" + 
					"        background-color: #f0f0eb;\r\n" + 
					"        padding: 1.4%;\r\n" + 
					"        box-shadow: 10px 10px 10px -9px black;\r\n" + 
					"    }\r\n" + 
					"</style>\r\n" + 
					"\r\n" + 
					"<body>\r\n" + 
					"    <center>\r\n" + 
					"        <div class=\"main\">\r\n" + 
					"            <h1>Movie: "+uPrimaryTitle+" added successfully.</h1>\r\n" + 
					"            <a href=\"homepage.html\" style=\"text-decoration:none\">Homepage</a> &nbsp &nbsp <a href=\"TopTenMovies.html\" style=\"text-decoration:none\">top_10_movies</a> &nbsp &nbsp\r\n" + 
					"            <a href=\"AddMovie.html\" style=\"text-decoration:none\">Add_Movie</a>\r\n" + 
					"            <div>\r\n" + 
					"                <div class=\"footer\">\r\n" + 
					"                    <p>&copycopyright-2022 all right reserved.</p>\r\n" + 
					"                </div>\r\n" + 
					"    </center>\r\n" + 
					"</body>\r\n" + 
					"\r\n" + 
					"</html>");
			out.flush();
			out.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
