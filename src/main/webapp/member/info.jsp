<%@page import="member.db.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
<%-- 	<%
	   MemberDTO dto = (MemberDTO)request.getAttribute("dto");
	%> --%>
	
	<h3> 아이디 : ${id }</h3>

	<h3> 이름 : ${dto.mem_name } </h3>

	<h2><a href="./Main.me">메인</a></h2>
	

</body>
</html>