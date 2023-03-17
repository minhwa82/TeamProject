<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>


	<%
		String S_num = request.getParameter("S_num");
		int T_num = Integer.parseInt(request.getParameter("T_num"));
		String Sc_num = request.getParameter("Sc_num");
		String M_num = request.getParameter("M_num");
		String alert = "";
		
		System.out.println(S_num);
		System.out.println(T_num);
		System.out.println(Sc_num);
		System.out.println("M"+M_num);
		
		int S_choice = 1;
		try{
			String driver = "com.mysql.cj.jdbc.Driver";
			Class.forName(driver);
			String url = "jdbc:mysql://db.itwillbs.dev:3306/class6_221128_team1";
			String userName = "class6_221128_team1";
			String userPass = "1234";
			Connection con = DriverManager.getConnection(url, userName, userPass);
			PreparedStatement pstmt = null;
			
			String sql = "select S_choice from seat join time "
					+ "on seat.T_num = time.T_num "
					+ "where seat.Sc_num=? and seat.T_num=? and time.M_num=? and S_num=? "
					+ "order by S_num";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, Sc_num);
			pstmt.setInt(2, T_num);
			pstmt.setString(3, M_num);
			pstmt.setString(4, S_num);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				S_choice = rs.getInt("S_choice");
				System.out.println(S_choice);
				
				if(S_choice == 0){
					sql = "update seat set S_choice=2 where S_num=? and T_num=? and Sc_num=?";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1 ,S_num);
					pstmt.setInt(2, T_num);
					pstmt.setString(3, Sc_num);
					
					pstmt.executeUpdate();
					alert = "";
					
				}else if(S_choice == 2){
					alert = "{ " + "\"alt\" : \"예매중인 좌석\"" + " }";
					//alert = "예매중인 좌석";
					JSONParser parser = new JSONParser();
					
					Object obj = parser.parse(alert);
					JSONObject data = (JSONObject)obj;
					
					//JSONObject data = (JSONObject)parser.parse(alert);
					
					System.out.print(data.get("alt"));
					
					response.getWriter().print(data);
					//out.print(alert);
					
				}
				
			}
			
			
			con.close();
			pstmt.close();
		} catch (Exception e){
			
		}

	%>
