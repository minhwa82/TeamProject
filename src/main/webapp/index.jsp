<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>index.jsp</h1>
	
	<%
		// MVC패턴에서 실행가능한 유일한 jsp페이지
		
	
		//response.sendRedirect("./itwill.me");
		//response.sendRedirect("./MemberJoin.me");
		//response.sendRedirect("./MemberLogin.me");
		//response.sendRedirect("./BoardWrite.bo");
		//response.sendRedirect("./BoardList.bo");
		response.sendRedirect("./main.mv");
		
	%>
	

</body>
</html>