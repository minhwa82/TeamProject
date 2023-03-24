<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Hello Movie</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script type="text/javascript">
$(function() {

	$('#menu > li').mouseover(function() {
		var menu_i = $(this).index();
		console.log("@@@@@" + menu_i);

		if (menu_i == 2) {
			$('.menu_pan').css({
				display : 'none'
			}).eq($(this).index() - 3).css({
				display : 'block'
			});
		} else {
			// 추가된 코드
			$('.menu_pan').css({
				display : 'none'
			});
		}
	});

	$('#menu').mouseleave(function() {
		$('.menu_pan').css({
			display : 'none'
		});
	});

	$('.menu_pan').mouseover(function() {
		$('.menu_pan').eq($(this).index() - 3).css({
			display : 'block'
		});
	});

	$('.menu_pan').mouseleave(function() {
		$('.menu_pan').eq($(this).index() - 3).css({
			display : 'none'
		});
	});
});
</script>
</head>
<body>
<nav class="main-nav overlay clearfix">
	<a class="blog-logo" href="./Main.mm"><img src="assets/img/logo.png" alt="Fashion Critiques"/></a>
	<ul id="menu">	
		<li class="nav-home nav-current"><a href="MovieList.mv">영화</a></li>
		<li class="nav-article-example"><a href="Booking.bk">예매</a></li>
		<li class="nav-about-us" style="margin-bottom: 20px;"><a href="#">극장</a></li>
		<li class="nav-author-page" role="presentation"><a href="Bonus.me">혜택</a></li>
		
	<span class="socialheader">
	<c:if test="${id == null }">
		<li><a href="Login.me">로그인</a></li>
		<li><a href="Join.me">회원가입</a></li>
	</c:if>

  	<c:if test="${id != null }">  
  		<%-- <li><a>	${dto.mem_name }님 </a></li> --%>
		<li><a href="MyTicket.me">예매확인</a></li>
		<li><a href="MyPage.me">마이페이지</a></li>
		<li><a href="LogoutAction.me">로그아웃</a></li>
	</c:if>
	
	<c:if test="${id != null && id.equals('admin') }">
		<li><a href="./AdminPage.mm">관리자</a></li>
	</c:if>

	</span>
	</ul>
	<div class="menu_pan">
		<div class="sub_menu">
			<div class="screen_zone">
				<a>서울</a>
				<div>
					<a href="./Screen.sc?Sc_num=02_1" title="강남">강남점</a>
				</div>
				<div>
					<a href="./Screen.sc?Sc_num=02_2" title="명동">명동점</a>
				</div>
			</div>
		</div>

		<div class="sub_menu">
			<div class="screen_zone">
				<a>대구</a>
				<div>
					<a href="./Screen.sc?Sc_num=053_1" title="대구수성">수성점</a>
				</div>
				<div>
					<a href="./Screen.sc?Sc_num=053_2" title="대구현대">현대점</a>
				</div>
			</div>
		</div>

		<div class="sub_menu">
			<div class="screen_zone">
				<a>대전</a>
				<div>
					<a href="./Screen.sc?Sc_num=042_1" title="대전탄방">탄방점</a>
				</div>
				<div>
					<a href="./Screen.sc?Sc_num=042_2" title="대전가오">가오점</a>
				</div>
			</div>
		</div>
		<div class="sub_menu">
			<div class="screen_zone">
				<a>부산</a>
				<div>
					<a href="./Screen.sc?Sc_num=051_1" title="부산서면">서면점</a>
				</div>
				<div>
					<a href="./Screen.sc?Sc_num=051_2" title="부산해운대">해운대점</a>
				</div>
			</div>
		</div>

		<div class="sub_menu">
			<div class="screen_zone">
				<a>광주</a>
				<div>
					<a href="./Screen.sc?Sc_num=062_1" title="대구수성">광주점</a>
				</div>
				<div>
					<a href="./Screen.sc?Sc_num=062_2" title="대구현대">용봉점</a>
				</div>
			</div>
		</div>

	</div>
</nav>

</body>
</html>