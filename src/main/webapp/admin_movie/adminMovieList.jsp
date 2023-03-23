<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<title>Nectaria - Free HTML Template by WowThemes.net</title>
<meta name="description" content="Thoughts, reviews and ideas since 1999."/>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="shortcut icon" href="#">
<link rel="stylesheet" type="text/css" href="assets/css/screen.css"/>
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:400,300italic,300,400italic,700,700italic|Playfair+Display:400,700,400italic,700italic"/>

<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>

</head>


<body class="author-template">
<div class="site-wrapper">
	<jsp:include page="../inc/top.jsp"/>
	<a class="blog-logo" href="./Main.mv"><img src="assets/img/logo.png" alt="Fashion Critiques"/></a>
	<header class="main-header author-head " style="background-image: url(http://s3.amazonaws.com/caymandemo/wp-content/uploads/sites/10/2015/09/30162427/sep2.jpg)">
	<jsp:include page="../inc/adminCenter.jsp"/>
	</header>
	
	<main class="content" role="main">
	<form action="./MovieWrite.mm" method="post" >	

	<input type ="hidden" name="M_num" value="${dto.M_num }">
	<table id="notice">
		<tr>
		    <th class="ttitle">제목</th>
		    <th class="ttitle">장르</th>
		    <th class="ttitle">시간</th>
		    <th class="ttitle">감독</th>
		    <th class="ttitle">관람등급</th>
		    <th class="ttitle">배우</th>
		    <th class="ttitle">포스터</th>
		    <th class="ttitle">개봉일</th>
		    <th class="ttitle">설명</th>
		    <th class="ttitle">평점</th>
		    <th class="ttitle">예매율</th>
		</tr>
		
		<c:forEach var="dto" items="${movieList }">
			<tr>
				<td class="left">${dto.m_name }</td>
				<td class="left">${dto.m_type }</td>
				<td class="left">${dto.m_runTime }</td>
				<td class="left">${dto.m_director }</td>
				<td class="left">${dto.m_age }</td>
				<td class="left">${dto.m_actor }</td>
				<td class="left">
				  <img src="./upload/${dto.m_img }" 
				       width="190px" height="200px" >
				</td>				
				<td class="left">${dto.m_playDate }</td>
				<td class="left">${dto.m_explain }</td>
				<td class="left">${dto.m_grade }</td>
				<td class="left">${dto.m_reservationRate }</td>
				
				<td>
				<a href="./MovieUpdateAction.mm?M_num=${dto.m_num}">수정</a>/
				<a href="./MovieDelteAction.mm?M_num=${dto.m_num}">삭제</a></td>
		   
		    </tr>
	    </c:forEach>
	    
	</table>
		
		
			<input type="submit" value="등록하기">
		</form>
	</main>
		<jsp:include page="../inc/footer.jsp"/>

</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="assets/js/masonry.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.fitvids.js"></script>
<script type="text/javascript" src="assets/js/index.js"></script>
</body>
</html>