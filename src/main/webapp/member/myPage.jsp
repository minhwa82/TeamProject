<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<title>My Page</title>
<meta name="description" content="Thoughts, reviews and ideas since 1999."/>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="shortcut icon" href="#">
<link rel="stylesheet" type="text/css" href="assets/css/screen.css"/>
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:400,300italic,300,400italic,700,700italic|Playfair+Display:400,700,400italic,700italic"/>

<style type="text/css">
.membercard {
  box-shadow: rgba(0, 0, 0, 0.15) 0px 5px 15px 0px;
    padding: 30px;
    display: inline-flex;
    flex-direction: column;
    align-items: center;
    width: 280px;
    height:250px;
    background: #fff;
}

.membercard .text{
    display: flex;
    flex-direction: column;
    align-items: center;
}

.membercard .text img{
	padding: 20px;
    width:  90px;
    height: 90px;
    border-radius: 50%;
    margin-bottom:10px;
}

.ticket {
	display: inline-block;
	margin-left: 50px ;
}

.btn-area button {
  width: 50%;
  height: 50px;
  margin-top: 50px;
  color: #fff;
  background: black;
  border: none;
  border-radius: 20px;
  font-size: 15px;
  cursor: pointer;
}

</style>
</head>
<body class="post-template page-template page">
<div class="site-wrapper">
<jsp:include page="../inc/top.jsp"/>.
	<header class="main-header post-head " style="background-image: url(http://s3.amazonaws.com/caymandemo/wp-content/uploads/sites/10/2015/10/10174958/fas-compressor.jpg)">

	<div class="vertical">
		<div class="main-header-content inner">
			<h1 class="post-title">My page</h1>
				<div class="author-meta">
					<span class=""><a href="">회원정보 수정</a></span>
					<span><a href="MyTicket.bk?Mem_num=${dto.mem_num }">예매확인</a></span> 
					<span class=""><a href="Bonus.me">등급확인</a></span>
					<span class=""><a href="">결제내역</a></span>
					<span class=""><a href="">1:1 문의내역</a></span>
					<span class=""><a href="">MY영화관</a></span>
					<span class=""><a href="Delete.me">회원탈퇴</a> </span>
				</div>
		</div>
	</div>
	</header>
	
 	<main class="content" role="main">
	<article class="post tag-fashion tag-art page">
	<section class="post-content">
		<!-- 프로필 -->
		<div class="membercard">
			<div class="text">
				<img src="./img/user.png">
				<h3>${dto.mem_name}님 </h3> 
				<h4> ${dto.mem_grade } 등급</h4>
			</div>
			<a href="MemberInfo.me">회원 정보</a>
		</div>
		<!-- 프로필 -->
	
		<!-- totalList 순서 : bdto, mvdto, scdto, tdto -->
		<div class="ticket">
		
			<!-- 예매 내역 없을 경우 -->
		    <c:if test="${empty totalList }">
				<h3>최근 예매 내역이 없습니다.</h3>
				<div class="btn-area">
					<button id="btn" type="submit" onclick="#">예매 바로가기</button>
				</div>
			</c:if>
			
			<c:forEach var="s" items="${totalList}">
				예매번호: ${s[0].b_booking_num}
				<h2> ${s[1].m_name } </h2>
				${s[2].sc_name } <br>
				${s[3].t_startTime } <br>
				좌석 번호 : ${s[0].s_num} <br>
				청소년 : ${s[0].youth_num}명 /
				성인 : ${s[0].adult_num}명 <br>
			</c:forEach>
			
		</div>


    </section>
	</article>
	</main> 


</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="assets/js/masonry.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.fitvids.js"></script>
<script type="text/javascript" src="assets/js/index.js"></script>
</body>
</html>