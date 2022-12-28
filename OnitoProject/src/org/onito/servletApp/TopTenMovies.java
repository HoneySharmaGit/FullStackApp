package org.onito.servletApp;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class TopTenMovies extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
	ServletException, IOException{
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from onito.movies order by runtimeMinutes desc limit 10";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=dinga");
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			PrintWriter out=resp.getWriter();
			int i=1;
			out.println("<!DOCTYPE html>\r\n" + 
					"<html>\r\n" + 
					"\r\n" + 
					"<head>\r\n" + 
					"    <meta charset=\"ISO-8859-1\">\r\n" + 
					"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
					"    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\r\n" + 
					"\r\n" + 
					"    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65\" crossorigin=\"anonymous\">\r\n" + 
					"    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js\"></script>\r\n" + 
					"\r\n" + 
					"    <title>Top 10 movie</title>\r\n" + 
					"    <style>\r\n" + 
					"        body {\r\n" + 
					"            background-image: url(\"movie.jpg\");\r\n" + 
					"            background-repeat: no-repeat;\r\n" + 
					"            background-attachment: fixed;\r\n" + 
					"            background-size: 100% 100%;\r\n" + 
					"        }\r\n" + 
					"\r\n" + 
					"        .footer {\r\n" + 
					"            position: fixed;\r\n" + 
					"            bottom: 0px;\r\n" + 
					"            height: 20px;\r\n" + 
					"            width: 100%;\r\n" + 
					"            background-color: grey;\r\n" + 
					"        }\r\n" + 
					"\r\n" +  
					"        h1 {\r\n" + 
					"            background-color: grey;\r\n" + 
					"            width: 45%;\r\n" + 
					"        }\r\n" + 
					"    </style>\r\n" + 
					"</head>\r\n" + 
					"\r\n" + 
					"<body>\r\n" + 
					"    <center>\r\n" + 
					"        <h1>TOP 10 Movies based on runtime</h1>\r\n" + 
					"        <div class=\"footer\">\r\n" + 
					"            <p>&copycopyright-2022 all right reserved.</p>\r\n" + 
					"        </div>\r\n" + 
					"    </center>\r\n" + 
					"</body>\r\n" + 
					"\r\n" + 
					"</html>");
			while(rs.next()) {
				String qtconst=rs.getString("tconst");
				String qprimaryTitle=rs.getString("primaryTitle");
				int qruntimeMinutes=rs.getInt("runtimeMinutes");
				String qgeners=rs.getString("genres");
				
				out.println("<!DOCTYPE html>\r\n" + 
						"<html>\r\n" + 
						"\r\n" +
						"<body>\r\n" + 
						"<h3 style=\"background-color:#caede8;\">\r\n"+" &nbsp  &nbsp &nbsp &nbsp ("+i+".)"+
						" &nbsp tconst: <b>"+qtconst+"</b>&nbsp | &nbsp primaryTitle: <b>"
						+ " "+qprimaryTitle+"</b> &nbsp | &nbsp runtimeMinutes: <b>"+qruntimeMinutes+"</b> &nbsp | &nbsp geners: <b>"
						+qgeners +"</b></h3>\r\n" + 
						"</body>\r\n" + 
						"\r\n" + 
						"</html>");
				i++;
			}
			out.flush();
			out.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
