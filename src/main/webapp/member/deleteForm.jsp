<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Thoughts, reviews and ideas since 1999."/>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="shortcut icon" href="#">
<link rel="stylesheet" href="./css/login.css">
<script src="../lib/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="assets/css/screen.css"/>
<link rel="stylesheet" type="text/css" 
href="https://fonts.googleapis.com/css?family=Roboto:400,300italic,300,400italic,700,700italic|Playfair+Display:400,700,400italic,700italic"/>
<title>회원 탈퇴</title>
</head>
<body class="post-template page-template page">
<jsp:include page="../inc/top.jsp"/>
	
	<!-- 비밀번호 입력받아서 삭제 -->
	<%
	String id = (String)session.getAttribute("id");
	if(id == null){
		response.sendRedirect("./Login.me");
	}
	%>

    	

<section class="login-form">
	<c:if test="${id != null && !id.equals('admin') }">
        <h1>${id} 님의 회원 탈퇴</h1> 
    </c:if>
    
        <form action="./Delete.me" method="post">
		<input type="hidden" name="id" value="<%=id%>">
		<div class="int-area">
       		<input type="password" name="pw" id="pw" autocomplete="off" required>
        <label for="pw">비밀번호</label>
        </div>
		<div class="btn-area">
                <button id="btn" type="submit">회원탈퇴</button>
        </div>
			
	</form>
</section>

</body>
</html>