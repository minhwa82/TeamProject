<%@page import="movie.main.db.MovieDTO"%>
<%@page import="member.db.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<title>Hello Movie</title>
<style type="text/css">
.notice1 {
	font-size: 20px;
}
img{
	text-align: center;
}
#reser{
	text-align: left;
	text-decoration: underline;
	color: black;
	font-weight: bold;
}
#ab{
	text-align: left;
}


</style>
<meta name="description" content="Thoughts, reviews and ideas since 1999."/>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="shortcut icon" href="#">
<link rel="stylesheet" type="text/css" href="assets/css/screen.css"/>
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:400,300italic,300,400italic,700,700italic|Playfair+Display:400,700,400italic,700italic"/>
</head>
<body class="home-template">
<div class="site-wrapper">
	<jsp:include page="../inc/top.jsp"/>
	<header class="main-header">
	<div class="vertical">
		<div class="main-header-content inner">
			<iframe width="750px" height="440px" src="https://www.youtube.com/embed/pqiZwIq6Vlo?mute=1&autoplay=1" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>			

			</div>
	
	<input type ="hidden" name="M_num" value="${dto.m_num }">
	
	<table>
	<div>
	<h3>
		<a href="#none" id="abc" style="text-decoration: none;"> 무비차트 </a>
		<a href="./movieDetails.mv" id="abc" class="btn-gradient green mini" > 전체보기 </a>
	</h3>
		
	</div>
	
			<c:forEach var="dto" items="${movieList }">
				<a href="./movieDetails?M_num=${dto.m_num }">	
				<div style="float:left">
				<img src="./upload/${dto.m_img }" onerror="this.style.display='none';"
					       width="245px" height="340px" alt=""></a>
					       <br>
					 <a href="./movieBooking?M_num=${dto.m_num }" class="btn-gradient red mini" > 예매하기 </a>
				</div>
	    </c:forEach>
	    <br>
	    
	    <div #reser>
		<%-- <c:forEach var="dto" items="${movieList }">
			<input type="text" value="예매율 ${dto.m_reservationRate }%" width="50px">
				
			<a href="#none"  class="btn-gradient yellow mini"  >
			<span style="color: black;"> 예매율${dto.m_reservationRate }%</span></a>		
			<a href="./movieBooking?M_num=${dto.m_num }" id="abc" class="btn-gradient red mini" > 예매하기 </a>
		

	    </c:forEach> --%>
	    </div>
			
	</table>
		</div>
	</div>
	</header>
	<jsp:include page="../inc/footer.jsp"/>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="assets/js/masonry.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.fitvids.js"></script>
<script type="text/javascript" src="assets/js/index.js"></script>
</body>
</html>