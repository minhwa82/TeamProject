<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<script src="https://code.jquery.com/jquery-3.6.3.js"
	integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM="
	crossorigin="anonymous"></script>
	
<script type="text/javascript">
	$(document).ready(function(){
		var now_utc = Date.now() // 지금 날짜를 밀리초로
		// getTimezoneOffset()은 현재 시간과의 차이를 분 단위로 반환
		var timeOff = new Date().getTimezoneOffset()*60000; // 분단위를 밀리초로 변환
		// new Date(now_utc-timeOff).toISOString()은 '2022-05-11T18:09:38.134Z'를 반환
		var today = new Date(now_utc-timeOff).toISOString().split("T")[0];
		document.getElementById("Date").setAttribute("min", today);
		
		
	});
</script>


</head>


<body class="author-template">
<div class="site-wrapper">
	<nav class="main-nav overlay clearfix">
	<a class="blog-logo" href="index.html"><img src="assets/img/logo.png" alt="Fashion Critiques"/></a>
	<ul id="menu">
		<li class="nav-home nav-current" role="presentation"><a href="index.html">Home</a></li>
		<li class="nav-article-example" role="presentation"><a href="article.html">Post Example</a></li>
		<li class="nav-about-us" role="presentation"><a href="about.html">Page Example</a></li>
		<li class="nav-author-page" role="presentation"><a href="author.html">Author Page</a></li>
		<span class="socialheader">
		<a href="#"><span class='symbol'>circletwitterbird</span></a>
		<a href="#"><span class='symbol'>circlefacebook</span></a>
		<a href="#"><span class='symbol'>circlegoogleplus</span></a>
		<a href="mailto:wowthemesnet@gmail.com"><span class='symbol'>circleemail</span></a>
		</span>
	</ul>
	</nav>
	<header class="main-header author-head " style="background-image: url(http://s3.amazonaws.com/caymandemo/wp-content/uploads/sites/10/2015/09/30162427/sep2.jpg)">
	</header>
	
	<main class="content" role="main">
	<div class="grid">
		<div class="grid-item">
			<article class="post">
			
			<form action="./timeInsertProAction.mv">
			
				상영점 : 
				<select name="Sc_num">
					<option value="" selected>상영점 선택</option>
					<c:forEach items="${sList }" var="sList">
						<option value="${sList.sc_num }">${sList.sc_zone } ${sList.sc_name }</option>
					</c:forEach>
					
				</select><br>
				
				영화명 : 
				<select name="M_num">
					<option value="" selected>영화명 선택</option>
					<c:forEach items="${List }" var="List">
						<option value="${List.m_num }">${List.m_name }</option>
					
					</c:forEach>
				</select><br>
				
				날짜 : 
				<input type="date" name="T_date" id="Date"><br>
				
				시작시간 : 
				<input type="time" name="T_startTime"><br>
				
				종료시간 : 
				<input type="time" name="T_endTime"><br>
				
				<input type="submit" value="등록">
				
			</form>
			
			</article>
		</div>
		
	</div>
	
	</main>
	<footer class="site-footer clearfix">
	<a href="#top" id="back-to-top" class="back-top"></a>
	<div class="text-center">
	Shared by <i class="fa fa-love"></i><a href="https://bootstrapthemes.co">BootstrapThemes</a>

	</div>
	</footer>
</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="assets/js/masonry.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.fitvids.js"></script>
<script type="text/javascript" src="assets/js/index.js"></script>
</body>
</html>