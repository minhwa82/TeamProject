<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<%
		String S_num = request.getParameter("S_num");
		int T_num = Integer.parseInt(request.getParameter("T_num"));
		String Sc_num = request.getParameter("Sc_num");
		try{
			/* String driver = "com.mysql.cj.jdbc.Driver";
			Class.forName(driver);
			String url = "jdbc:mysql://db.itwillbs.dev:3306/class6_221128_team1";
			String userName = "class6_221128_team1";
			String userPass = "1234";
			Connection con = DriverManager.getConnection(url, userName, userPass);
			Statement pstmt = con.createStatement();
			
			String sql = "update seat set S_choice=2 where S_num=? and T_num=? and Sc_num=?";
			pstmt.setString()
			ResultSet rs = pstmt.executeQuery(sql); */
			
			/* while(rs.next()){
				
			}
			con.close();
			pstmt.close(); */
		} catch (Exception e){
			
		}

	%>
	<%=S_num %>
	<%=T_num %>
	<%=Sc_num %>


</body>
</html>