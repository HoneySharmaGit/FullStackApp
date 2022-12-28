package org.onito.servletApp;

import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class MoviesRating extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
	ServletException, IOException{
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select movies.tconst, primaryTitle, genres, averageRating from onito.ratings, onito.movies where movies.tconst=ratings.tconst and ratings.averageRating>6 order by ratings.averageRating desc";
		
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
            		"    <title>Movie Rating Page</title>\r\n" + 
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
            		"\r\n" + 
            		"    .footer {\r\n" + 
            		"        position: fixed;\r\n" + 
            		"        bottom: 0px;\r\n" + 
            		"        height: 20px;\r\n" + 
            		"        width: 100%;\r\n" + 
            		"        background-color: grey;\r\n" + 
            		"    }\r\n" + 
            		"\r\n" + 
            		"    h1 {\r\n" + 
            		"        background-color: grey;\r\n" + 
            		"        width: 45%;\r\n" + 
            		"    }\r\n" + 
            		"</style>\r\n" + 
            		"\r\n" + 
            		"\r\n" + 
            		"<body>\r\n" + 
            		"    <center>\r\n" + 
            		"        <h1>Movies List whose rating>6.0</h1>\r\n" +
            		"<div class=\"footer\">"+
            	    "<p>&copycopyright-2022 all right reserved.</p>"+
            	    "</div>"+
            		"    </center>\r\n" + 
            		"</body>\r\n" + 
            		"\r\n" + 
            		"</html>");
            while(rs.next()) {
    			String qTconst=rs.getString("movies.tconst");
    			String qPrimaryTitle=rs.getString("primaryTitle");
    			String qGenre=rs.getString("genres");
    			int qRating=rs.getInt("averageRating");
    			
    			out.println("<!DOCTYPE html>\r\n" +  
    					"<html>\r\n" + 
						"\r\n" +
    					"<body>\r\n" + 
    					"    <h3 style=\"background-color:#caede8\";> &nbsp "+
    					"&nbsp &nbsp &nbsp" +"("+i+".)"+" &nbsp tconst: <b>"+qTconst+"</b>"
    							+ "&nbsp | &nbsp primaryTitle: <b>"+qPrimaryTitle+"</b> &nbsp | &nbsp "
    									+ "gener: <b>"+qGenre+"</b> &nbsp | &nbsp rating: <b>"
    											+ ""+qRating+"</b></h3>\r\n" + 
    					"</body>\r\n" + 
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
